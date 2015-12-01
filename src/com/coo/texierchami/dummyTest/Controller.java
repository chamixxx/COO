package com.coo.texierchami.dummyTest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class Controller implements Runnable {

    private ServerSocket socketserver;
    private Socket socket;
    private int nbrclient = 1;

    public Controller(ServerSocket s){
        socketserver = s;
    }

    @Override
    public void run() {

        try {
            while(true){
                socket = socketserver.accept(); // Un client se connecte on l'accepte
                System.out.println("Le client numéro "+nbrclient+ " est connecté !");
                nbrclient++;
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
