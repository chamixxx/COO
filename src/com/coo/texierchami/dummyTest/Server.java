package com.coo.texierchami.dummyTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class Server {
    public static void main(String[] zero) {

        ServerSocket socketserver  ;
        Socket socketduserveur ;
        BufferedReader in;
        PrintWriter out;

        try {

            socketserver = new ServerSocket(2010);
            Thread thread = new Thread(new Controller(socketserver));
            thread.start();

            System.out.println("Le serveur est à l'écoute du port "+socketserver.getLocalPort());
            socketduserveur = socketserver.accept();
            System.out.println("Un zéro s'est connecté !");

            out = new PrintWriter(socketduserveur.getOutputStream());
            out.println("Vous êtes connecté zéro !");
            out.flush();

            socketserver.close();
            socketduserveur.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
