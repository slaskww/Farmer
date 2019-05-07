package farmer.game;

import farmer.animals.FarmAnimal;

public class Board {


    private final Pen [] pens;
    private static final int SIZE = 5; //rabbit, sheep, pig, cow, horse

    public Board() {
        this.pens = new Pen [SIZE];
    }

    public Pen[] getPens() {
        return pens;
    }

    public void setPen(Pen pen, int index) {

    }



}
