package com.coo.texierchami.connexion;


import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by othmanechamikhazraji on 26/11/15.
 */
public class CommunicationClient {
    private Socket socket = null;
    private Thread  receptionThread, emissiomThread;
    private Emission emission;
    private Reception reception;

    public CommunicationClient(int port) {
        try {
            System.out.println(InetAddress.getLocalHost());

            socket = new Socket(InetAddress.getLocalHost(),port);
            System.out.println("Demande de connexion");
            emission = new Emission(socket);
            emissiomThread = new Thread(emission);

            reception = new Reception(socket);
            receptionThread = new Thread(reception);

            emissiomThread.start();
            receptionThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Object object) {
        emission.setObject(object);
        System.out.print("jecris au server");
    }

    public Object getObjectFromSocket() {
        return reception.getObjectFromSocket();
    }

}
