package com.chess.engine.player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.pieces.Alliance;
import com.chess.engine.pieces.Piece;
import com.chess.engine.pieces.Rook;
import com.google.common.collect.ImmutableList;

public class BlackPlayer extends Player {

	public BlackPlayer(final Board board, final Collection<Move> whiteLegalMoves, 
									final Collection<Move> blackLegalMoves) {
		
		super(board, blackLegalMoves, whiteLegalMoves);
		
	}

	@Override
	public Collection<Piece> getActivePieces() {
		
		return this.board.getBlackPieces();
	}

	@Override
	public Alliance getAlliance() {
		
		return Alliance.BLACK;
	}

	@Override
	public Player getOpponent() {
		
		return this.board.whitePlayer();
	}

	 @Override
	    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
	                                                    final Collection<Move> opponentLegals) {

	        if(this.isInCheck() || this.isCastled() || !(this.isKingSideCastleCapable() || this.isQueenSideCastleCapable())) {
	            return ImmutableList.of();
	        }

	        final List<Move> kingCastles = new ArrayList<>();

	        if(this.king.isFirstMove() && this.king.getPiecePosition() == 60 && !this.isInCheck()) {
	            //whites king side castle
	            if(this.board.getPiece(61) == null && this.board.getPiece(62) == null) {
	                final Piece kingSideRook = this.board.getPiece(63);
	                if(kingSideRook != null && kingSideRook.isFirstMove()) {
	                    if(Player.calculateAttacksOnTile(61, opponentLegals).isEmpty() && Player.calculateAttacksOnTile(62, opponentLegals).isEmpty() &&
	                       kingSideRook.getPieceType().isRook()) {
	                        if(!BoardUtils.isKingPawnTrap(this.board, this.king, 52)) {
	                            kingCastles.add(new KingSideCastleMove(this.board, this.king, 62, (Rook) kingSideRook, kingSideRook.getPiecePosition(), 61));
	                        }
	                    }
	                }
	            }
	            //whites queen side castle
	            if(this.board.getPiece(59) == null && this.board.getPiece(58) == null &&
	               this.board.getPiece(57) == null) {
	                final Piece queenSideRook = this.board.getPiece(56);
	                if(queenSideRook != null && queenSideRook.isFirstMove()) {
	                    if(Player.calculateAttacksOnTile(58, opponentLegals).isEmpty() &&
	                       Player.calculateAttacksOnTile(59, opponentLegals).isEmpty() && queenSideRook.getPieceType().isRook()) {
	                        if(!BoardUtils.isKingPawnTrap(this.board, this.playerKing, 52)) {
	                            kingCastles.add(new QueenSideCastleMove(this.board, this.playerKing, 58, (Rook) queenSideRook, queenSideRook.getPiecePosition(), 59));
	                        }
	                    }
	                }
	            }
	        }
	        return ImmutableList.copyOf(kingCastles);
	    }

}
