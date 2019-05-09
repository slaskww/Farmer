package farmer.game;

import java.util.ArrayList;
import java.util.Iterator;

public class PensIterator implements Iterator<Pen> {

   private Board board;
   private ArrayList<Pen> pens;
   private Iterator<Pen> innerIterator;

    public PensIterator(Board board) {
        this.board = board;

        pens = new ArrayList<>();

        for (Pen pen : board.getPens()){
            pens.add(pen);
        }
        innerIterator = pens.iterator();
    }



    @Override
    public boolean hasNext() {
        return innerIterator.hasNext();
    }

    @Override
    public Pen next() {
        return innerIterator.next();
    }
}


