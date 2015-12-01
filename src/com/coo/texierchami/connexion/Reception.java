package com.coo.texierchami.connexion;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class Reception implements Runnable {

    private Socket socket;
    private ObjectInputStream objectInputStream;
    private Object object;

    public Reception(Socket socket){
        this.socket = socket;
        try {
            objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getObjectFromSocket() {
        if (object != null){
            Object objectToSend = object;
            object = null;
            return objectToSend;
        }
        return null;
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(500);
                object = objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
