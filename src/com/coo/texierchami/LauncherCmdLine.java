package com.coo.texierchami;

import com.coo.texierchami.controler.controlerLocal.ChessGameControler;
import com.coo.texierchami.model.observable.ChessGameLocal;
import com.coo.texierchami.view.ChessGameCmdLine;


/**
 * @author francoise.perrin
 * Lance l'exécution d'un jeu d'échec en mode console.
 */
public class LauncherCmdLine {
	
	public static void main(String[] args) {		
		
		ChessGameLocal chessGameLocal;
		ChessGameControler chessGameControler;
		
		chessGameLocal = new ChessGameLocal();
		chessGameControler = new ChessGameControler(chessGameLocal);
		
		new ChessGameCmdLine(chessGameControler);
	}

}
