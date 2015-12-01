package com.coo.texierchami.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by othmanechamikhazraji on 01/12/15.
 */
public class MessageModelSocket implements Serializable {
    private List<PieceIHM> pieceIHMList;
    private String message;

    public MessageModelSocket(List<PieceIHM> list, String message) {
        pieceIHMList = list;
        this.message = message;
    }

    public List<PieceIHM> getPieceIHMList() {
        return pieceIHMList;
    }

    public String getMessage() {
        return message;
    }


}
