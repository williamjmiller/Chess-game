
public abstract class Tile {
	
	int tileCoord;
	
	// Constructor
	
	Tile(int tileCoord) { 
		this.tileCoord = tileCoord;
	}
	
	public abstract boolean isTileOccupied();
	
	// Will get the piece of a tile
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile {
		
		EmptyTile(int coordinate) { 
			super(coordinate);
		}
	
		@Override
		public boolean isTileOccupied() {
			return false;
		}
			
		@Override
		public Piece getPiece() { 
			return null;
		}
		
	}
}