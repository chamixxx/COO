package com.coo.texierchami.connexion;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class Emission implements Runnable {

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private Object object = null;

    public Emission(Socket socket) {
        this.socket = socket;
        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(500);
                if (object != null) {
                    objectOutputStream.writeObject(object);
                    object = null;
                    objectOutputStream.flush();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setObject(Object object) {
        this.object = object;
    }
}
