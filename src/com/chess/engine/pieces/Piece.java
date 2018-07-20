package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {

	protected final PieceType pieceType;
	protected final int piecePosition;
	protected final Alliance pieceAlliance; // alliance = black or white
	protected final boolean isFirstMove;
	private final int cachedHashCode;

	Piece(final PieceType pieceType, final int piecePosition, final Alliance pieceAlliance) {

		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
		this.isFirstMove = false;
		this.pieceType = pieceType;
		this.cachedHashCode = computeHashCode();
	}
	
	private int computeHashCode() {
		int result = pieceType.hashCode();
		result = 31 * result + pieceAlliance.hashCode();
		result = 31 * result + piecePosition;
		result = 31 * result + (isFirstMove() ? 1 : 0);
		return result;
	}

	@Override
	public boolean equals(final Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof Piece)) {
			return false;
		}
		final Piece otherPiece = (Piece) other;
		return piecePosition == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType() 
				&& pieceAlliance == otherPiece.getPieceAlliance() && isFirstMove == otherPiece.isFirstMove();
	}

	@Override
	public int hashCode() {

		return this.cachedHashCode;
	}
	
	public int getPiecePosition() {
		return this.piecePosition;
	}

	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}

	public boolean isFirstMove() {
		return this.isFirstMove();
	}

	public PieceType getPieceType() {
		return this.pieceType;
	}

	// takes a given piece and calculates its legal moves
	public abstract Collection<Move> calculateMoves(final Board board);
	
	// create a "new" piece with a new location since piece is immutable
	public abstract Piece movePiece(Move move);

	public enum PieceType {

		PAWN("P") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		}, KNIGHT("N") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		}, ROOK("R") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		}, BISHOP("B") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		}, QUEEN("Q") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return false;
			}
		}, KING("K") {
			@Override
			public boolean isKing() {
				// TODO Auto-generated method stub
				return true;
			}
		};

		private String pieceName;

		PieceType(final String pieceName) {
			this.pieceName = pieceName;
		}

		@Override
		public String toString() {
			return this.pieceName;
		}
		
		public abstract boolean isKing();
	}

}