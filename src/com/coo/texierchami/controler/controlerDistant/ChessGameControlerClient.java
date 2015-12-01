package com.coo.texierchami.controler.controlerDistant;

import com.coo.texierchami.connexion.CommunicationClient;
import com.coo.texierchami.controler.ChessGameControlers;
import com.coo.texierchami.model.Coord;
import com.coo.texierchami.model.Couleur;
import com.coo.texierchami.model.MessageModelSocket;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class ChessGameControlerClient extends Observable implements ChessGameControlers, Runnable {

    private CommunicationClient communicationClient;
    private Thread reception;
    private Object messageModelSocket;




    public ChessGameControlerClient(int port) {
        communicationClient = new CommunicationClient(port);
        reception = new Thread(this);
        reception.start();
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        List<Coord> coordList = new ArrayList<>();
        coordList.add(initCoord);
        coordList.add(finalCoord);
        communicationClient.write(coordList);
        return false;
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
    public void run() {

        while (true) {
            try {
                Thread.sleep(500);
                messageModelSocket = communicationClient.getObjectFromSocket();
                if (messageModelSocket != null) {
                    System.out.print("jai recut \n");
                    setChanged();
                    notifyObservers(messageModelSocket);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void notifyObservers() {
        List<Coord> coordList = new ArrayList<>();
        communicationClient.write(coordList);
    }
}
