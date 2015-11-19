package com.coo.texierchami;

import com.coo.texierchami.controler.controlerLocal.ChessGameControler;
import com.coo.texierchami.model.observable.ChessGame;
import com.coo.texierchami.view.ChessGameCmdLine;


/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode console.
 */
public class LauncherCmdLine {
	
	public static void main(String[] args) {		
		
		ChessGame chessGame;
		ChessGameControler chessGameControler;
		
		chessGame = new ChessGame();	
		chessGameControler = new ChessGameControler(chessGame);
		
		new ChessGameCmdLine(chessGameControler);
	}

}
