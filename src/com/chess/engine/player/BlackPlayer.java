package com.chess.engine.player;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Alliance;
import com.chess.engine.pieces.Piece;

public class BlackPlayer extends Player {

	public BlackPlayer(final Board board, final Collection<Move> whiteLegalMoves, 
									final Collection<Move> blackLegalMoves) {
		
		super(board, blackLegalMoves, whiteLegalMoves);
		
	}

	@Override
	public Collection<Piece> getActivePieces() {
		
		return this.board.getBlackPieces();
	}

	@Override
	public Alliance getAlliance() {
		
		return Alliance.BLACK;
	}

	@Override
	public Player getOpponent() {
		
		return this.board.whitePlayer();
	}

}
