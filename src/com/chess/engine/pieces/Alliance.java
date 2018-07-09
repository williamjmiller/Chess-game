package com.chess.engine.pieces;

public enum Alliance {
	
	WHITE {
		@Override
		public int getDirection() {
			return -1;
		}
		
		@Override
		public boolean isWhite() {
			return true;
		}

		@Override
		public boolean isBlack() {
			return false;
		}
	},
	
	BLACK {
		@Override
		public int getDirection() {
			return 1;
		}
		
		@Override
		public boolean isBlack() {
			return true;
		}

		@Override
		public boolean isWhite() {
			return false;
		}
	};
	
	public abstract int getDirection();
	public abstract boolean isBlack();
	public abstract boolean isWhite();
}
