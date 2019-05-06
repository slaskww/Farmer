package farmer.game;

import farmer.animals.FarmAnimal;

public class Board {

    private final Pen [] pens;
    private static final int SIZE = 5; //rabbit, sheep, pig, cow, horse

    public Board(Pen[] pens) {
        this.pens = pens;
    }

    public Pen[] getPens() {
        return pens;
    }

    public void putAnimalIntoPen(FarmAnimal animal, int index) {

    }


}
