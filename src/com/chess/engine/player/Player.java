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
		
		
		Player(final Board board,
				final Collection<Move> legalMoves,
				final Collection<Move> opponentMoves) {
			
			this.board = board;
			this.king = estKing();
			this.legalMoves = legalMoves;
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
		
		public abstract Collection<Piece> getActivePieces();
		public abstract Alliance getAlliance();
		public abstract Player getOpponenent();
	
}
