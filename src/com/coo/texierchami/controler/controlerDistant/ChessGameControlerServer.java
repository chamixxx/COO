package com.coo.texierchami.controler.controlerDistant;

import com.coo.texierchami.connexion.CommunicationServer;
import com.coo.texierchami.controler.ChessGameControlers;
import com.coo.texierchami.model.Coord;
import com.coo.texierchami.model.Couleur;
import com.coo.texierchami.model.observable.ChessGameDistant;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class ChessGameControlerServer implements ChessGameControlers, Observer, Runnable{

    private ChessGameDistant chessGameDistant;
    private CommunicationServer communicationServerClient;

    private Thread reception, connexion;
    private Object coordsObject;

    public ChessGameControlerServer(ChessGameDistant chessGameDistant) {
        this.chessGameDistant = chessGameDistant;
        communicationServerClient = new CommunicationServer(9200,9300);
        connexion = new Thread(communicationServerClient);
        connexion.start();
        reception = new Thread(this);
        reception.start();
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        boolean hasMoved = chessGameDistant.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
        return hasMoved;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return null;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.print("jupdate");
        communicationServerClient.write(arg);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            coordsObject = communicationServerClient.getObjectFromSocket();
            if (coordsObject != null) {
                List<Coord> coordList = (List<Coord>) coordsObject;
                if (coordList.size() == 0) {
                   chessGameDistant.notifyObservers();
                }
                else {
                    move(coordList.get(0),coordList.get(1));
                }
            }
        }
    }


}
