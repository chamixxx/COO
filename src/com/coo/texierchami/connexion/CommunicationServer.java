package com.coo.texierchami.connexion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class CommunicationServer implements Runnable{
    private static ServerSocket serverSocket0, serverSocket1;
    private Thread  receptionThread0, emissionThread0,
            receptionThread1, emissionThread1;
    private Emission emission0, emission1;
    private Reception reception0, reception1;
    private Socket socket0, socket1;


    public CommunicationServer(int port0, int port1) {
        try {
            serverSocket0 = new ServerSocket(port0);
            System.out.println("Le serveur est à l'écoute du port "
                    +serverSocket0.getLocalPort());
            serverSocket1 = new ServerSocket(port1);
            System.out.println("Le serveur est à l'écoute du port "
                    +serverSocket1.getLocalPort());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Object object) {
        if (emission0 != null) {
            emission0.setObject(object);
        }
        if (emission1 != null) {
            emission1.setObject(object);
        }
    }

    public Object getObjectFromSocket() {
        Object object0, object1;
        if (reception0 != null ) {
            object0 = reception0.getObjectFromSocket();
            if (object0 !=null)
                return object0;
        }
        if (reception1 != null) {
            object1 = reception1.getObjectFromSocket();
            if (object1 !=null)
                return object1;
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            try {
                socket0 = serverSocket0.accept();
                System.out.println("Le client 0 se connecte");
                emission0 = new Emission(socket0);
                emissionThread0 = new Thread(emission0);

                reception0 = new Reception(socket0);
                receptionThread0 = new Thread(reception0);

                emissionThread0.start();
                receptionThread0.start();

                socket1 = serverSocket1.accept();
                System.out.println("Le client 1 se connecte");

                emission1 = new Emission(socket1);
                emissionThread1 = new Thread(emission1);

                reception1 = new Reception(socket1);
                receptionThread1 = new Thread(reception1);

                emissionThread1.start();
                receptionThread1.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
