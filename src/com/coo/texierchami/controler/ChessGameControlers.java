package com.coo.texierchami.controler;

import com.coo.texierchami.model.Coord;
import com.coo.texierchami.model.Couleur;

/**
 * Created by othmanechamikhazraji on 13/11/15.
 */
public interface ChessGameControlers {
    boolean move(Coord initCoord, Coord finalCoord);
    String getMessage();
    boolean isEnd();
    Couleur getColorCurrentPlayer();
}
