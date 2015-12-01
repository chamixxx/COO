package com.coo.texierchami.controler.controlerLocal;

import com.coo.texierchami.controler.ChessGameControlers;
import com.coo.texierchami.model.Coord;
import com.coo.texierchami.model.Couleur;
import com.coo.texierchami.model.observable.ChessGameLocal;

/**
 * Created by othmanechamikhazraji on 13/11/15.
 */
public class ChessGameControler implements ChessGameControlers {
    private ChessGameLocal chessGameLocal;

    public ChessGameControler(ChessGameLocal chessGameLocal) {
        this.chessGameLocal = chessGameLocal;
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        boolean hasMoved = chessGameLocal.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
        return hasMoved;
    }

    @Override
    public String getMessage() {
        return chessGameLocal.getMessage();
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return chessGameLocal.getColorCurrentPlayer();
    }
}
