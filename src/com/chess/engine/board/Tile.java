package com.chess.engine.board;

import java.util.HashMap;
import java.util.Map;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

// Recall that we cannot instantiate an abstract class i.e. "New Tile"
public abstract class Tile {
	
	protected final int tileCoord;
	
	private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createEmptyTiles();
	
	private static Map<Integer, EmptyTile> createEmptyTiles() {
		final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
		
		for (int i = 0; i < BoardUtils.NUM_TILES; i++) { 
			emptyTileMap.put(i, new EmptyTile(i));
		}
		return ImmutableMap.copyOf(emptyTileMap);
	}
	
	// only way to create a new tile is through his factory method
	public static Tile createTile(final int tileCoord, final Piece piece) {
		return piece != null ? new OccupiedTile(tileCoord, piece) : EMPTY_TILES_CACHE.get(tileCoord);
	}
	
	// Constructor
	private Tile(final int tileCoord) { 
		this.tileCoord = tileCoord;
	}
	
	public abstract boolean isTileOccupied();
	
	// Will get the piece on a tile
	public abstract Piece getPiece();
	
	public static final class EmptyTile extends Tile {
		
		private EmptyTile(int coordinate) { 
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
	
	public static final class OccupiedTile extends Tile { 
		private final Piece pieceOnTile;
		
		private  OccupiedTile(int coordinate, final Piece pieceOnTile) { 
			super(coordinate);
			this.pieceOnTile = pieceOnTile;
		}
		
		@Override
		public boolean isTileOccupied() { 
			return true;
		}
		
		@Override
		public Piece getPiece() { 
			return pieceOnTile;
		}
	}
}