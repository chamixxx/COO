package com.coo.texierchami.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by othmanechamikhazraji on 19/11/15.
 */
public class ChessGameMessageGUI extends JFrame {
    JTextArea messageArea;

    public ChessGameMessageGUI() {
        super("Messages");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        messageArea = new JTextArea(50,25);
        messageArea.setVisible(true);
        messageArea.setText("Le Jeu commence :  \n\n");
        messageArea.setEditable(false);
        messageArea.setWrapStyleWord(true);
        messageArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public void addMessage (String message) {
        messageArea.append(message + "\n");
    }

}
