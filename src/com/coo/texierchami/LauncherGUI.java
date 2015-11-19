package com.coo.texierchami;
import com.coo.texierchami.controler.controlerLocal.ChessGameControler;
import com.coo.texierchami.model.observable.ChessGame;
import com.coo.texierchami.view.ChessGameGUI;

import javax.swing.*;

public class LauncherGUI {

    public static void main(String[] args) {
        ChessGame chessGame;
        ChessGameControler chessGameControler;

        chessGame = new ChessGame();
        chessGameControler = new ChessGameControler(chessGame);
        ChessGameGUI chessGameGUI = new ChessGameGUI(chessGameControler);
        chessGame.addObserver(chessGameGUI);

        chessGameGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        chessGameGUI.pack();
        chessGameGUI.setResizable(true);
        chessGameGUI.setLocationRelativeTo( null );
        chessGameGUI.setVisible(true);
        chessGame.notifyObservers();
    }
}