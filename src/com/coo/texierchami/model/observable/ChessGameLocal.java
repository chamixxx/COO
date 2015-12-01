package com.coo.texierchami.model.observable;

import com.coo.texierchami.model.Couleur;
import com.coo.texierchami.model.Echiquier;

import java.util.Observable;

/**
 * Created by othmanechamikhazraji on 13/11/15.
 */
public class ChessGameLocal extends Observable implements ChessGames {
    private Echiquier echiquier;

    public ChessGameLocal() {
        echiquier = new Echiquier();
    }

    @Override
    public boolean move(int xInit, int yInit, int xFinal, int yFinal) {

        Boolean isMoveOk = echiquier.isMoveOk(xInit, yInit, xFinal, yFinal);
        if (!isMoveOk) {
            System.out.print("Déplacement non autorisé \n");
            setChanged();
            notifyObservers(isMoveOk);
            return false;
        }
        else {
            echiquier.move(xInit, yInit, xFinal, yFinal);
            setChanged();
            notifyObservers(isMoveOk);
            echiquier.switchJoueur();
            return true;
        }
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public String getMessage() {
        return echiquier.getMessage();
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return echiquier.getColorCurrentPlayer();
    }

    @Override
    public void notifyObservers () {
        setChanged();
        notifyObservers(echiquier.getPiecesIHM());
    }
}
