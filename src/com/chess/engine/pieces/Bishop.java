package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.Move.AttackMove;
import com.chess.engine.board.Move.MajorMove;
import com.chess.engine.pieces.Piece.PieceType;
import com.google.common.collect.ImmutableList;

public class Bishop extends Piece {

	/*
	 * If the tiles of the 64 count board were numbered left to right, then the
	 * bishops' potential moves can be calculated using vectors to achieve the
	 * diagonal movement. At most 14 legal moves for a bishop of a given location.
	 */

	private final static int[] POTENTIAL_MOVE_VECTOR_COORDINATES = { -9, -7, 7, 9 };

	public Bishop(final Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.BISHOP, piecePosition, pieceAlliance);

	}

	@Override
	public Collection<Move> calculateMoves(Board board) {

		final List<Move> legalMoves = new ArrayList<>();

		for (final int potentialCoordinateOffset : POTENTIAL_MOVE_VECTOR_COORDINATES) {

			int potentialDestinationCoordinate = this.piecePosition;

			while (BoardUtils.isValidTileCoordinate(potentialDestinationCoordinate)) {
				
				if (isFirstColumnEx(potentialDestinationCoordinate, potentialCoordinateOffset)
						|| isEighthColumnEx(potentialDestinationCoordinate, potentialCoordinateOffset)) {

					break;
				}
				
				potentialDestinationCoordinate += potentialCoordinateOffset;

				if (BoardUtils.isValidTileCoordinate(potentialDestinationCoordinate)) {

					final Tile potentialDestinationTile = board.getTile(potentialDestinationCoordinate);

					if (!potentialDestinationTile.isTileOccupied()) {
						legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));

					} else {

						final Piece pieceAtDestination = potentialDestinationTile.getPiece();
						final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

						// If Tile is not occupied
						if (this.pieceAlliance != pieceAlliance) {
							legalMoves.add(new AttackMove(board, this, potentialDestinationCoordinate, pieceAtDestination));
						}
						break;
					}
				}
			}
		}

		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public String toString() {
		return Piece.PieceType.BISHOP.toString();
	}

	// edge cases
	private static boolean isFirstColumnEx(final int currentPosition, final int potentialOffset) {

		return BoardUtils.FIRST_COLUMN[currentPosition] && (potentialOffset == -9 || potentialOffset == 7);
	}

	private static boolean isEighthColumnEx(final int currentPosition, final int potentialOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (potentialOffset == 9 || potentialOffset == -7);
	}

	@Override
	public Bishop movePiece(Move move) {
		
		return new Bishop(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}

}
