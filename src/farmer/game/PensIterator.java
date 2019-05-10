package farmer.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PensIterator implements Iterator<Pen> {

   private Board board;
   private List<Pen> pens;
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
        Pen pen = innerIterator.next();
      //  System.out.println("in hasNext, PensIterator, next animal: " + pen.getAnimal().getName());
        return pen ;
    }
}


