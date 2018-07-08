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
import com.google.common.collect.ImmutableList;
public class Pawn extends Piece {

	private final static int[] CANDIDATE_MOVE_COORDINATE = {8};
	
	Pawn(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		
	}

	@Override
	public Collection<Move> calculateMoves(Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for (final int currentOffsetCoordinate : CANDIDATE_MOVE_COORDINATE) {
		
			int potentialDestinationCoordinate = this.piecePosition + 
					this.getPieceAlliance().getDirection() * currentOffsetCoordinate;
			
			if (BoardUtils.isValidTileCoordinate(potentialDestinationCoordinate)) {
				continue;
			}
			
			if (currentOffsetCoordinate == 8 && !board.getTile(potentialDestinationCoordinate).isTileOccupied()) {
				legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}

}
