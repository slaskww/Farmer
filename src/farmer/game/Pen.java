package farmer.game;

import farmer.animals.FarmAnimal;

public class Pen { //  separated part of the farm where each type of animals are kept

   private FarmAnimal animal;
   private int size;

    public Pen(FarmAnimal animal, int size) {
        this.animal = animal;
        this.size = size;
    }

    public FarmAnimal getAnimal() {
        return animal;
    }

    public int getSize() {
        return size;
    }

    public void setAnimal(FarmAnimal animal) {
        this.animal = animal;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
