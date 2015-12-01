package com.coo.texierchami;

import com.coo.texierchami.controler.controlerDistant.ChessGameControlerServer;
import com.coo.texierchami.model.observable.ChessGameDistant;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class LauncherServer {

    public static void main(String[] args) {
        ChessGameDistant chessGameDistant = new ChessGameDistant();
        ChessGameControlerServer chessGameControlerServer = new ChessGameControlerServer(chessGameDistant);
        chessGameDistant.addObserver(chessGameControlerServer);
    }


    ChessGameDistant chessGameDistant = new ChessGameDistant();
    ChessGameControlerServer chessGameControlerServer = new ChessGameControlerServer(chessGameDistant);


}
