package com.coo.texierchami.view;

import com.coo.texierchami.controler.controlerDistant.ChessGameControlerClient;
import com.coo.texierchami.controler.controlerLocal.ChessGameControler;
import com.coo.texierchami.model.Coord;
import com.coo.texierchami.model.PieceIHM;
import com.coo.texierchami.tools.ChessImageProvider;
import com.coo.texierchami.tools.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;

/**
 * Created by othmanechamikhazraji on 25/11/15.
 */
public class ChessGameGUIDistantBourrin extends JFrame implements MouseListener, MouseMotionListener, Observer {
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private Coord coordInitiales;
    private Coord coordFinales;
    private final ChessGameControlerClient chessGameControler;
    private boolean isFirst = true;
    private Component componentDrop;
    private ChessGameMessageGUI messages;

    public ChessGameGUIDistantBourrin(ChessGameControlerClient chessGameControler) {
        this.chessGameControler = chessGameControler;
        Dimension boardSize = new Dimension(600, 600);

        messages = new ChessGameMessageGUI();


        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            else
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        int indexComponent = Util.getComponentIndex(c.getParent());
        int x = indexComponent % 8;
        int y = indexComponent / 8;
        coordInitiales = new Coord(x, y);

        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) return;

        //chessPiece.setVisible(false);
        componentDrop = chessBoard.findComponentAt(e.getX(), e.getY());

        int indexComponent;
        if (componentDrop instanceof JLabel) {
            indexComponent = Util.getComponentIndex(componentDrop.getParent());
        } else {
            indexComponent = Util.getComponentIndex(componentDrop);
        }

        int x = indexComponent % 8;
        int y = indexComponent / 8;
        coordFinales = new Coord(x, y);

        System.out.print("init: " + coordInitiales + "     finale: " + coordFinales + "\n");
        chessGameControler.move(coordInitiales, coordFinales);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (chessPiece == null) return;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void update(Observable o, Object arg) {

        if (chessPiece != null) {
            layeredPane.remove(chessPiece);
        }

        for (int i=0; i<64 ; i++) {
            JPanel panel = (JPanel) chessBoard.getComponent(i);
            panel.removeAll();
        }

        java.util.List<PieceIHM> pieceIHMList = (java.util.List<PieceIHM>) arg;
        java.util.List<Coord> coordonees;
        for (PieceIHM piece : pieceIHMList) {
            coordonees = piece.getList();
            for (Coord coord : coordonees) {
                int position = coord.x + coord.y * 8;
                JLabel pieceGUI = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(piece.getTypePiece(), piece.getCouleur())));
                JPanel panel = (JPanel) chessBoard.getComponent(position);
                panel.add(pieceGUI);
            }
        }
        revalidate();
        repaint();
        displayMesages();
    }

    private void displayMesages() {
        String message = chessGameControler.getMessage();
        messages.addMessage(message);
    }
}
