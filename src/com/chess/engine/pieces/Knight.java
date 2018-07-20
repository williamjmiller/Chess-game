package com.chess.engine.pieces;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.pieces.Piece.PieceType;
import com.google.common.collect.ImmutableList;

import static com.chess.engine.board.Move.*;

public class Knight extends Piece {

	/* If the tiles of the 64 count board were numbered left to right, then the knights' 
	 * potential moves can be calculated using arithmetic to achieve the 'L' shape. At most 8
	 * legal moves for a knight of a given location
	 */
	private final static int[] POTENTIAL_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};
	
	public Knight(final Alliance pieceAlliance, final int piecePosition) {
		super(PieceType.KNIGHT, piecePosition, pieceAlliance);
		
	}

	@Override
	public Collection<Move> calculateMoves(final Board board) {
		
		final List<Move> legalMoves = new ArrayList<>();
		
		for(int i = 0; i < POTENTIAL_MOVE_COORDINATES.length; i++) { 
			
			final int potentialDestinationCoordinate = this.piecePosition + POTENTIAL_MOVE_COORDINATES[i];
			
			if (BoardUtils.isValidTileCoordinate(potentialDestinationCoordinate)) { 
				
				if(isFirstColumnEx(this.piecePosition, i) || isSecondColumnEx(this.piecePosition, i) ||
						isSeventhColumnEx(this.piecePosition, i) || isEighthColumnEx(this.piecePosition, i)) {
					
							continue;
				}
		
				
				final Tile potentialDestinationTile = board.getTile(potentialDestinationCoordinate);
				
				if(!potentialDestinationTile.isTileOccupied()) {
					legalMoves.add(new MajorMove(board, this, potentialDestinationCoordinate));
				
				} else {
					
					final Piece pieceAtDestination = potentialDestinationTile.getPiece();
					final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();
					
					// If Tile is not occupied
					if (this.pieceAlliance != pieceAlliance) {
						legalMoves.add(new AttackMove(board, this, potentialDestinationCoordinate, pieceAtDestination));
					}
				}
			}
		}
		
		return ImmutableList.copyOf(legalMoves);
	}
	
	@Override
	public Knight movePiece(Move move) {
		
		return new Knight(move.getMovedPiece().getPieceAlliance(), move.getDestinationCoordinate());
	}
	
	@Override
	public String toString() {
		return Piece.PieceType.KNIGHT.toString();
	}
	
	// edge cases
	private static boolean isFirstColumnEx(final int currentPosition, final int potentialOffset) {
		
		return BoardUtils.FIRST_COLUMN[currentPosition] && (potentialOffset == -17 || potentialOffset == -10 ||
				potentialOffset == 6 || potentialOffset == 15);
	}
	
	private static boolean isSecondColumnEx(final int currentPosition, final int potentialOffset) {
		return BoardUtils.SECOND_COLUMN[currentPosition] &&  (potentialOffset == -10 ||
				potentialOffset == 6);
	}
	
	private static boolean isSeventhColumnEx(final int currentPosition, final int potentialOffset) {
		return BoardUtils.SEVENTH_COLUMN[currentPosition] &&  (potentialOffset == 10 ||
				potentialOffset == -6);
	}
	
	private static boolean isEighthColumnEx(final int currentPosition, final int potentialOffset) {
		return BoardUtils.EIGHTH_COLUMN[currentPosition] && (potentialOffset == 17 || potentialOffset == 10 ||
				potentialOffset == -6 || potentialOffset == -15);
	}
}
