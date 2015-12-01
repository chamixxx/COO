package com.coo.texierchami;

import com.coo.texierchami.controler.controlerDistant.ChessGameControlerClient;
import com.coo.texierchami.view.ChessGameGUIDistantBourrin;

import javax.swing.*;


/**
 * Created by othmanechamikhazraji on 26/11/15.
 */
public class LauncherClient {
    public static void main(String[] args) {


        for (int i=0;i<2;i++) {
            int port = 9200 + 100*i;
            ChessGameControlerClient chessGameControlerClient;
            chessGameControlerClient = new ChessGameControlerClient(port);
            ChessGameGUIDistantBourrin chessGameGUIDistantBourrin =
                    new ChessGameGUIDistantBourrin(chessGameControlerClient);
            chessGameControlerClient.addObserver(chessGameGUIDistantBourrin);
            chessGameGUIDistantBourrin.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            chessGameGUIDistantBourrin.pack();
            chessGameGUIDistantBourrin.setResizable(true);
            chessGameGUIDistantBourrin.setLocationRelativeTo( null );
            chessGameGUIDistantBourrin.setVisible(true);
            chessGameControlerClient.notifyObservers();
        }

    }
}
