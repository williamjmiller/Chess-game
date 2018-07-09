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
import com.google.common.collect.ImmutableList;

public class King extends Piece {

	/*
	 * If the tiles of the 64 count board were numbered left to right, then the
	 * bishops' potential moves can be calculated using vectors to achieve the
	 * diagonal movement. At most 14 legal moves for a bishop of a given location.
	 */

	private final static int[] POTENTIAL_MOVE_COORDINATE = { -1, -9, -8, -9, 1, 7, 8, 9 };

	King(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);

	}

	@Override
	public Collection<Move> calculateMoves(Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentOffsetCoordinate : POTENTIAL_MOVE_COORDINATE) {

			final int potentialDestinationCoordinate = this.piecePosition + currentOffsetCoordinate;

			if (BoardUtils.isValidTileCoordinate(potentialDestinationCoordinate)) {
				
				if (isFirstColumnEx(potentialDestinationCoordinate, currentOffsetCoordinate)
						|| isEighthColumnEx(potentialDestinationCoordinate, currentOffsetCoordinate)) {

					continue;
				}
				
				final Tile potentialDestinationTile = board.getTile(potentialDestinationCoordinate);
				
				if(!potentialDestinationTile.isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));
				
				} else {
					
					final Piece pieceAtDestination = potentialDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					// If Tile is not occupied
					if (this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new AttackMove(board, this, potentialDestinationCoordinate, pieceAtDestination));
					}
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
		
	}
	// edge cases
		private static boolean isFirstColumnEx(final int currentPosition, final int potentialOffset) {
			
			return BoardUtils.FIRST_COLUMN[currentPosition] && (potentialOffset == -1 || potentialOffset == 7 ||
					potentialOffset == -9);
		}
		
		private static boolean isEighthColumnEx(final int currentPosition, final int potentialOffset) {
			return BoardUtils.EIGHTH_COLUMN[currentPosition] && (potentialOffset == 1 || potentialOffset == -7 ||
					potentialOffset == 9);
		}
}
