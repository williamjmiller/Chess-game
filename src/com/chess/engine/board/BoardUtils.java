package com.chess.engine.board;

public class BoardUtils {
	
	public static final boolean[] FIRST_COLUMN = populateColumn(0);
	public static final boolean[] SECOND_COLUMN = populateColumn(1);
	public static final boolean[] SEVENTH_COLUMN = populateColumn(6);
	public static final boolean[] EIGHTH_COLUMN = populateColumn(7);
	
	public static final int NUM_TILES = 64;
	public static final int NUM_TILES_PER_ROW = 8;
	
	// algorithm for initializing the column arrays
	private static boolean[] populateColumn(int remainder) {
		
		final boolean[] column = new boolean[64];
		
		for (int i = 0; i < column.length; i++) {
			if (i % 8 == remainder) {
				column[i] = true;
			}
		}
		
		return column;
		
	}
	
	private BoardUtils() {
		throw new RuntimeException("Class cannot be instantiated");
	}
	
	// checks if move is out of the Board's scope
	public static boolean isValidTileCoordinate(final int coordinate) {
		return coordinate >=0 && coordinate <= 63;
	} 
	

	
}
