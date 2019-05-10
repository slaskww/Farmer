package util;

import farmer.game.Board;
import farmer.game.Pen;

import java.util.Iterator;

public class Main {

    public static void main(String[] args) {


        Board board = new Board();
        board.showPens();

        Iterator<Pen> iter = board.penIterator();
        while (iter.hasNext()){
            Pen pen = iter.next();
            System.out.println(pen.getAnimal().getName());

        }

    }
}
