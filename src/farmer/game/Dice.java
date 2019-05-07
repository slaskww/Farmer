package farmer.game;

import farmer.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Dice {

    private List<Animal> sides;
    public final int NUMBER_OF_SIDES_ON_THE_DIE = 12;


    public Dice() {
        this.sides = new ArrayList<>();
    }

    public void placeAnimalOnTheDice(Animal animal){
        if (!isPlacingAvailable()){
            throw new IllegalArgumentException("All animals were placed on the die");
        }
        this.sides.add(animal);
    }

    public boolean isPlacingAvailable(){
        return this.sides.size() != NUMBER_OF_SIDES_ON_THE_DIE;
    }
}
