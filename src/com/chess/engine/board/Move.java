package com.chess.engine.board;

import com.chess.engine.board.Board.Builder;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;

public abstract class Move {

	final Board board;
	final Piece movedPiece;
	final int destinationCoordinate;
	
	public static final Move NULL_MOVE = new NullMove();

	// Constructor
	private Move(final Board board, final Piece movedPiece, final int destinationCoordinate) {

		this.board = board;
		this.movedPiece = movedPiece;
		this.destinationCoordinate = destinationCoordinate;
		//this.isFirstMove = false;
	}

	private Move(final Board board, final int destinationCoordinate) {
		this.board = board;
		this.destinationCoordinate = destinationCoordinate;
		this.movedPiece = null;
		//this.isFirstMove = false;
	}

	public int getDestinationCoordinate() {
		return this.destinationCoordinate;
	}

	public Piece getMovedPiece() {
		return this.movedPiece;
	}

	public Board execute() {

		final Board.Builder builder = new Builder();

		for (final Piece piece : this.board.currentPlayer().getActivePieces()) {
			if (!this.movedPiece.equals(piece)) {
				builder.setPiece(piece);
			}
		}

		for (final Piece piece : this.board.currentPlayer().getOpponent().getActivePieces()) {
			builder.setPiece(piece);
		}

		// move the piece by this call
		builder.setPiece(null);
		builder.setMoveMaker(this.board.currentPlayer().getOpponent().getAlliance());
		return builder.build();

	}

	public static final class MajorMove extends Move {

		public MajorMove(final Board board, final Piece movedPiece, final int destinationCoordinate) {
			super(board, movedPiece, destinationCoordinate);
		}
	}

	public static class AttackMove extends Move {

		final Piece attackedPiece;

		public AttackMove(Board board, Piece movedPiece, int destinationCoordinate, final Piece attackedPiece) {
			super(board, movedPiece, destinationCoordinate);

			this.attackedPiece = attackedPiece;
		}

		@Override
		public Board execute() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public static class PawnMove extends Move {

		public PawnMove(final Board board, final Piece pieceMoved, final int destinationCoordinate) {
			super(board, pieceMoved, destinationCoordinate);
		}
	}

	public static class PawnAttackMove extends AttackMove {

		public PawnAttackMove(final Board board, final Piece pieceMoved, final int destinationCoordinate,
				final Piece pieceAttacked) {
			super(board, pieceMoved, destinationCoordinate, pieceAttacked);
		}
	}

	public static class PawnEnPassantAttack extends PawnAttackMove {

		public PawnEnPassantAttack(final Board board, final Piece pieceMoved, final int destinationCoordinate,
				final Piece pieceAttacked) {
			super(board, pieceMoved, destinationCoordinate, pieceAttacked);
		}
	}

	public static class PawnJump extends Move {

		public PawnJump(final Board board, final Pawn pieceMoved, final int destinationCoordinate) {
			super(board, pieceMoved, destinationCoordinate);
		}
	}

	static abstract class CastleMove extends Move {

		final Rook castleRook;
		final int castleRookStart;
		final int castleRookDestination;

		CastleMove(final Board board, final Piece pieceMoved, final int destinationCoordinate, final Rook castleRook,
				final int castleRookStart, final int castleRookDestination) {
			super(board, pieceMoved, destinationCoordinate);
			this.castleRook = castleRook;
			this.castleRookStart = castleRookStart;
			this.castleRookDestination = castleRookDestination;
		}
	}
	
	public int getCurrentCoordinate() {
		return this.getMovedPiece().getPiecePosition();
	}

	public static class KingSideCastleMove extends CastleMove {

		public KingSideCastleMove(final Board board, final Piece pieceMoved, final int destinationCoordinate,
				final Rook castleRook, final int castleRookStart, final int castleRookDestination) {
			super(board, pieceMoved, destinationCoordinate, castleRook, castleRookStart, castleRookDestination);
		}
	}

	public static class QueenSideCastleMove extends CastleMove {

		public QueenSideCastleMove(final Board board, final Piece pieceMoved, final int destinationCoordinate,
				final Rook castleRook, final int castleRookStart, final int rookCastleDestination) {
			super(board, pieceMoved, destinationCoordinate, castleRook, castleRookStart, rookCastleDestination);
		}
	}

	private static class NullMove extends Move {

		private NullMove() {
			super(null, -1);
		}

		@Override
		public int getCurrentCoordinate() {
			return -1;
		}

		@Override
		public int getDestinationCoordinate() {
			return -1;
		}

		@Override
		public Board execute() {
			throw new RuntimeException("cannot execute null move");
		}

		@Override
		public String toString() {
			return "Null Move";
		}
	}
	
	 public static class MoveFactory {

	        private static final Move NULL_MOVE = new NullMove();

	        private MoveFactory() {
	            throw new RuntimeException("Not instantiatable!");
	        }

	        public static Move getNullMove() {
	            return NULL_MOVE;
	        }

	        public static Move createMove(final Board board,
	                                      final int currentCoordinate,
	                                      final int destinationCoordinate) {
	            for (final Move move : board.getAllLegalMoves()) {
	                if (move.getCurrentCoordinate() == currentCoordinate &&
	                    move.getDestinationCoordinate() == destinationCoordinate) {
	                    return move;
	                }
	            }
	            return NULL_MOVE;
	        }
	    }

}
