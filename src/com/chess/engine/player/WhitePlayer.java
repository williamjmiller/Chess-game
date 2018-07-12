package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Alliance;
import com.chess.engine.pieces.Piece;

public class WhitePlayer extends Player {

	public WhitePlayer(Board board, Collection<Move> whiteLegalMoves, 
									Collection<Move> blackLegalMoves) {
		
		super(board, whiteLegalMoves, blackLegalMoves);
		
	}

	@Override
	public Collection<Piece> getActivePieces() {
		
		return this.board.getWhitePieces();
	}

	@Override
	public Alliance getAlliance() {
		
		return Alliance.WHITE;
	}

	@Override
	public Player getOpponenent() {
		
		return null;
	}

}
