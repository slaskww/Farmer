package farmer.game;

import farmer.animals.Animal;
import farmer.animals.AnimalFactory;

import java.util.ArrayList;
import java.util.List;

public class AnimalsToPlaceOnDieCreator {

    private static List<Animal> animalsToPlaceOnDie;

    public AnimalsToPlaceOnDieCreator() {

        animalsToPlaceOnDie = new ArrayList<Animal>(){};
        animalsToPlaceOnDie.add(AnimalFactory.rabbit());
        animalsToPlaceOnDie.add(AnimalFactory.rabbit());
        animalsToPlaceOnDie.add(AnimalFactory.rabbit());
        animalsToPlaceOnDie.add(AnimalFactory.rabbit());
        animalsToPlaceOnDie.add(AnimalFactory.rabbit());
        animalsToPlaceOnDie.add(AnimalFactory.rabbit());
        animalsToPlaceOnDie.add(AnimalFactory.sheep());
        animalsToPlaceOnDie.add(AnimalFactory.sheep());
        animalsToPlaceOnDie.add(AnimalFactory.pig());
        animalsToPlaceOnDie.add(AnimalFactory.pig());
        animalsToPlaceOnDie.add(AnimalFactory.cow());
        animalsToPlaceOnDie.add(AnimalFactory.horse());
        animalsToPlaceOnDie.add(AnimalFactory.wolf());
        animalsToPlaceOnDie.add(AnimalFactory.fox());
    }

    public static List<Animal> getAnimalsToPlaceOnDie() {
        return animalsToPlaceOnDie;
    }
}
