package com.chess.engine.pieces;

import java.util.Collection;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

public abstract class Piece {

	protected final int piecePosition;
	protected final Alliance pieceAlliance; // alliance = black or white
	protected final boolean isFirstMove;
	
	Piece(final int piecePosition, final Alliance pieceAlliance) {
		this.piecePosition = piecePosition;
		this.pieceAlliance = pieceAlliance;
		this.isFirstMove = false;
	}
	
	public int getPiecePosition() {
		return this.getPiecePosition();
	}
	
	public Alliance getPieceAlliance() {
		return this.pieceAlliance;
	}
	
	public boolean isFirstMove() {
		return this.isFirstMove();
	}
	
	// takes a given piece and calculates its legal moves
	public abstract Collection<Move> calculateMoves(final Board board);
	
}