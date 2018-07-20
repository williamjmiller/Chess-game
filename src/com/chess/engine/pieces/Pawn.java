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

public class Pawn extends Piece {

	private final static int[] POTENTIAL_MOVE_COORDINATE = { 8, 16, 7, 9 };

	public Pawn(final Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.PAWN, piecePosition, pieceAlliance);

	}

	@Override
	public Collection<Move> calculateMoves(Board board) {

		final List<Move> legalMoves = new ArrayList<>();

		for (final int currentOffsetCoordinate : POTENTIAL_MOVE_COORDINATE) {

			final int potentialDestinationCoordinate = this.piecePosition
					+ this.getPieceAlliance().getDirection() * currentOffsetCoordinate;

			if (BoardUtils.isValidTileCoordinate(potentialDestinationCoordinate)) {
				continue;
			}
			
			// handles non-attacking pawn move
			if (currentOffsetCoordinate == 8 && !board.getTile(potentialDestinationCoordinate).isTileOccupied()) {
				// TODO
				legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));
					
			// handles pawn "jump"
			} else if (currentOffsetCoordinate == 16 && this.isFirstMove() && BoardUtils.SECOND_ROW[this.piecePosition]
					&& this.pieceAlliance.isBlack()
					|| BoardUtils.SEVENTH_ROW[this.piecePosition] && this.pieceAlliance.isWhite()) {
				final int behindPotentialDestinationCoordinate = this.piecePosition
						+ (this.pieceAlliance.getDirection() * 8);
				if (!board.getTile(behindPotentialDestinationCoordinate).isTileOccupied()
						&& !board.getTile(potentialDestinationCoordinate).isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));
				}
				
			// edge cases
			} else if(currentOffsetCoordinate == 7 &&
					!((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance().isWhite())
					|| BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance().isBlack())) {
				if (board.getTile(potentialDestinationCoordinate).isTileOccupied()) {
					final Piece pieceOnCandidate = board.getTile(potentialDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						// TODO
						legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));
					}
				}
				
			} else if (currentOffsetCoordinate == 9 &&
					!((BoardUtils.EIGHTH_COLUMN[this.piecePosition] && this.getPieceAlliance().isBlack())
					|| BoardUtils.FIRST_COLUMN[this.piecePosition] && this.getPieceAlliance().isWhite())) {
				if (board.getTile(potentialDestinationCoordinate).isTileOccupied()) {
					final Piece pieceOnCandidate = board.getTile(potentialDestinationCoordinate).getPiece();
					if (this.pieceAlliance != pieceOnCandidate.getPieceAlliance()) {
						legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));
					}
				}

			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public Pawn movePiece(Move move) {
		
		return new Pawn(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
	
	@Override
	public String toString() {
		return Piece.PieceType.PAWN.toString();
	}

}
