package com.coo.texierchami.dummyTest;

import com.coo.texierchami.connexion.Emission;
import com.coo.texierchami.model.Coord;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class Client {
    public static void main(String[] zero) {

        Socket socket;
        BufferedReader in;
        ObjectOutputStream out;

        try {

            socket = new Socket(InetAddress.getLocalHost(),9200);
            System.out.println("Demande de connexion");
            Emission emission = new Emission(socket);
            Thread tE = new Thread(emission);
            tE.start();


            Coord coordInit = new Coord(1,1);
            Coord coordFinal = new Coord(2,2);
            List<Coord> list = new ArrayList<>();
            list.add(coordInit);
            list.add(coordFinal);
            emission.setObject(list);


        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
