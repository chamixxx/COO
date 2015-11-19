package com.coo.texierchami.model.observable;

import com.coo.texierchami.model.Couleur;

/**
 * Created by othmanechamikhazraji on 13/11/15.
 */
public interface ChessGames {
    String toString();
    boolean move (int xInit, int yInit, int xFinal, int yFinal);
    boolean isEnd();
    String getMessage();
    Couleur getColorCurrentPlayer();
}
