package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Alliance;
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableList;

public class WhitePlayer extends Player {

	public WhitePlayer(final Board board, final Collection<Move> whiteLegalMoves, 
									final Collection<Move> blackLegalMoves) {
		
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
	public Player getOpponent() {
		
		return this.board.blackPlayer();
	}

	@Override
	protected Collection<Move> calculateKingCastles(Collection<Move> playerLegalMoves,
			Collection<Move> opponentLegalMoves) {
		
		final List<Move> kingCastle = new ArrayList<>();
		
		
		return ImmutableList.copyOf(kingCastle);
	}

}
