package com.coo.texierchami;
import com.coo.texierchami.controler.controlerLocal.ChessGameControler;
import com.coo.texierchami.model.observable.ChessGameLocal;
import com.coo.texierchami.view.ChessGameGUI;

import javax.swing.*;

public class LauncherGUI {

    public static void main(String[] args) {
        ChessGameLocal chessGameLocal;
        ChessGameControler chessGameControler;

        chessGameLocal = new ChessGameLocal();
        chessGameControler = new ChessGameControler(chessGameLocal);
        ChessGameGUI chessGameGUI = new ChessGameGUI(chessGameControler);
        chessGameLocal.addObserver(chessGameGUI);

        chessGameGUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        chessGameGUI.pack();
        chessGameGUI.setResizable(true);
        chessGameGUI.setLocationRelativeTo( null );
        chessGameGUI.setVisible(true);
        chessGameLocal.notifyObservers();
    }
}