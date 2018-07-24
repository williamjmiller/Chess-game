package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Alliance;
import com.chess.engine.pieces.Bishop;
import com.chess.engine.pieces.King;
import com.chess.engine.pieces.Knight;
import com.chess.engine.pieces.Pawn;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.Rook;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.WhitePlayer;
import com.google.common.collect.ImmutableList;

public abstract class Player {

	
		protected final Board board;
		
		protected final King king;
		protected final Collection<Move> legalMoves;
		private final boolean isInCheck;
		
		
		Player(final Board board,
				final Collection<Move> legalMoves,
				final Collection<Move> opponentMoves) {
			
			this.board = board;
			this.king = estKing();
			this.legalMoves = legalMoves;
			this.isInCheck = !Player.calculateAttacksOnTile(this.king.getPiecePosition(), opponentMoves).isEmpty();
		}
		
		public King getKing() {
			return this.king;
		}
		
		public Collection<Move> getLegalMoves() {
			return this.legalMoves;
		}
		private static Collection<Move> calculateAttacksOnTile(int piecePosition, Collection<Move> moves) {
			final List<Move> attackMoves = new ArrayList<>();
			for (final Move move : moves) {
				if(piecePosition == move.getDestinationCoordinate()) {
					attackMoves.add(move);
				}
			}
			return ImmutableList.copyOf(attackMoves);
		}
		
		private King estKing() {
			for(final Piece piece : getActivePieces()) {
				if(piece.getPieceType().isKing()) {
					return (King) piece;
				}
			}
			throw new RuntimeException("Not a Valid Board");
		}
		
		public boolean isMoveLegal(final Move move) {
			return this.legalMoves.contains(move);
		}
		
		public boolean isInCheck() {
			return this.isInCheck;
		}
		
		public boolean isInCheckMate() {
			return this.isInCheck && !hasEscapeMoves();
		}
		
		public boolean isInStaleMate() {
			return !this.isInCheck && !hasEscapeMoves();
		}
		
		public boolean isCastled() {
			return false;
		}
		
		protected boolean hasEscapeMoves() {
			
			for(final Move move : this.legalMoves) {
				final MoveTransition transition = makeMove(move);
				if (transition.getMoveStatus().isDone()) {
					return true;
				}
			}
			return false;
		}
		
		
		public MoveTransition makeMove(final Move move) {
			
			if(!isMoveLegal(move)) {
				return new MoveTransition(this.board, move, MoveStatus.ILLEGAL_MOVE);
			}
			
			final Board transitionBoard = move.execute();
			
			final Collection<Move> kingAttacks = Player.calculateAttacksOnTile(transitionBoard.currentPlayer().getOpponent().getKing().getPiecePosition(),
					transitionBoard.currentPlayer().getLegalMoves());
			
			if (!kingAttacks.isEmpty()) {
				return new MoveTransition(this.board, move, MoveStatus.LEAVES_PLAYER_IN_CHECK);
			}
			
			return new MoveTransition(transitionBoard, move, MoveStatus.DONE);
		}
		
		public abstract Collection<Piece> getActivePieces();
		public abstract Alliance getAlliance();
		public abstract Player getOpponent();
		protected abstract Collection<Move> calculateKingCastles(Collection<Move> playerLegalMoves, Collection<Move> opponentLegalMoves);
		
	
}
