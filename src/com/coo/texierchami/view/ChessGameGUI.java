package com.coo.texierchami.view;

import com.coo.texierchami.controler.controlerLocal.ChessGameControler;
import com.coo.texierchami.model.Coord;
import com.coo.texierchami.model.PieceIHM;
import com.coo.texierchami.tools.ChessImageProvider;
import com.coo.texierchami.tools.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

/**
 * Created by othmanechamikhazraji on 13/11/15.
 */
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {
    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private Coord coordInitiales;
    private Coord coordFinales;
    private final ChessGameControler chessGameControler;
    private boolean isFirst = true;
    private Component componentDrag;

    public ChessGameGUI(ChessGameControler chessGameControler) {
        this.chessGameControler = chessGameControler;
        Dimension boardSize = new Dimension(600, 600);

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

        chessPiece.setVisible(false);
        componentDrag = chessBoard.findComponentAt(e.getX(), e.getY());

        int indexComponent;
        if (componentDrag instanceof JLabel) {
            indexComponent = Util.getComponentIndex(componentDrag.getParent());
        }
        else {
            indexComponent = Util.getComponentIndex(componentDrag);
        }

        int x = indexComponent % 8;
        int y = indexComponent / 8;
        coordFinales = new Coord(x, y);

        System.out.print("init: " + coordInitiales + "     finale: " + coordFinales);
        boolean isMoveOk = chessGameControler.move(coordInitiales, coordFinales);
        if (!isMoveOk) {
            Component panel = chessBoard.getComponent(coordInitiales.x + coordInitiales.y * 8);
            Container parent = (Container)panel;
            parent.add( chessPiece );
            chessPiece.setVisible(true);
        }
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
        List<PieceIHM> pieceIHMList = (List<PieceIHM>) arg;
        List<Coord> coordonees;

        if (isFirst) {
            for (PieceIHM piece : pieceIHMList) {
                coordonees = piece.getList();
                for (Coord coord : coordonees) {
                    int position = coord.x + coord.y * 8;
                    JLabel pieceGUI = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(piece.getTypePiece(), piece.getCouleur())));
                    JPanel panel = (JPanel) chessBoard.getComponent(position);
                    if (panel.getComponentCount() != 0) {
                       panel.remove(0);
                    }
                    panel.add(pieceGUI);
                }
            }
            revalidate();
            isFirst = false;
        }
        else {
            if (componentDrag instanceof JLabel){
                Container parent = componentDrag.getParent();
                parent.remove(0);
                parent.add( chessPiece );
            }
            else {
                Container parent = (Container)componentDrag;
                parent.add( chessPiece );
            }

            chessPiece.setVisible(true);
        }
    }
}
