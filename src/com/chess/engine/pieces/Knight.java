package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

public class Knight extends Piece {

	/* If the tiles of the 64 count board were numbered left to right, then the knights' 
	 * potential moves can be calculated using arithmetic to achieve the 'L' shape. At most 8
	 * legal moves for a knight of a given location
	 */
	private final static int[] POTENTIAL_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	Knight(int piecePosition, Alliance pieceAlliance) {
		super(piecePosition, pieceAlliance);
		
	}

	@Override
	public List<Move> calculateMoves(Board board) {
		
		int potentialDestinationCoordinate;
		final List<Move> legalMoves = new ArrayList<>();
		
		for(int i = 0; i < POTENTIAL_MOVE_COORDINATES.length; i++) { 
			
			potentialDestinationCoordinate = this.piecePosition + POTENTIAL_MOVE_COORDINATES[i];
			
			if (true /* isValidTileCoordinate */) { 
				final Tile potentialDestinationTile = board.getTile(potentialDestinationCoordinate);
				
				if(!potentialDestinationTile.isTileOccupied()) {
					legalMoves.add(new Move());
				
				} else {
					
					final Piece pieceAtDestination = potentialDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					if (this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new Move());
					}
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}

}
