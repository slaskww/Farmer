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

    public void addAnimal(int number) {

        if (number < 0){
            throw new IllegalArgumentException("This value cannot be negative");
        }
        this.size += number;
    }

    public void removeAnimal(int number) {

        if (number >= this.size){
            throw new IllegalArgumentException("This value exceeds the size of pen");
        }

        if (number < 0){
            throw new IllegalArgumentException("This value cannot be negative");
        }

        this.size -= number;
    }
}
