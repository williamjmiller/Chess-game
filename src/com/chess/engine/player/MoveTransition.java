package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;

// information we want to capture in move to another board

public class MoveTransition {

		private final Board transitionBoard;
		private Move move;
		private final MoveStatus moveStatus; 
		
		public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus) {
			this.transitionBoard = transitionBoard;
			this.move = move;
			this.moveStatus = moveStatus;
		}
}
