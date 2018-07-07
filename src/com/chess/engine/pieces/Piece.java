package com.chess.engine.pieces;

import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {

	protected final int piecePosition;
	protected final Alliance pieceAlliance; // alliance = black or white
	
	Piece(final int piecePosition, final Alliance pieceAlliance) {
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
	}
	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	
	// takes a given piece and calculates its legal moves
	public abstract List<Move> calculateMoves(final Board board);
	
}