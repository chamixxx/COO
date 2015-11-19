package com.coo.texierchami.controler.controlerLocal;

import com.coo.texierchami.controler.ChessGameControlers;
import com.coo.texierchami.model.Coord;
import com.coo.texierchami.model.Couleur;
import com.coo.texierchami.model.observable.ChessGame;

/**
 * Created by othmanechamikhazraji on 13/11/15.
 */
public class ChessGameControler implements ChessGameControlers {
    private ChessGame chessGame;

    public ChessGameControler(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        boolean hasMoved = chessGame.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
        return hasMoved;
    }

    @Override
    public String getMessage() {
        return chessGame.getMessage();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return chessGame.getColorCurrentPlayer();
    }
}
