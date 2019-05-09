package farmer.game;

import farmer.animals.Animal;
import farmer.animals.AnimalFactory;

import java.util.ArrayList;
import java.util.List;

public class DiceBuilder {

   private Dice dice;
   private List<Animal> animalsToPutOnTheDice;

    public DiceBuilder() {
        this.dice = new Dice();
        animalsToPutOnTheDice = AnimalsToPlaceOnDieCreator.getAnimalsToPlaceOnDie();
    }

    public Dice build() {
        return this.dice;
    }

    private void placeAnimalOnTheDice(Animal animal) {
        this.dice.placeAnimalOnTheDice(animal);
    }

    private void placeAnimal(Animal animal) {
        placingAvailable();
        placingThisSpeciesAvailable(animal);
        placeAnimalOnTheDice(animal);
        updateAnimalsToPutOnTheDice(animal);
    }

    private void placingAvailable() {
        if (!this.dice.isPlacingAvailable()) {
            throw new IllegalArgumentException("All animals have already been placed on the die");
        }
    }

    private void placingThisSpeciesAvailable(Animal animal) {

        for (Animal anim : animalsToPutOnTheDice) {
            if (anim.getName().equals(animal.getName())) {
                return;
            }
            throw new IllegalArgumentException("all animals of this species have already been placed on the die");
        }

    }

    private void updateAnimalsToPutOnTheDice(Animal animal) {
        for (Animal anim : animalsToPutOnTheDice) {
            if (anim.getName().equals(animal.getName())) {
                animalsToPutOnTheDice.remove(anim);
                break;
            }
        }
    }

    public DiceBuilder placeRabbit() {
        Animal animal = AnimalFactory.rabbit();
        placeAnimal(animal);
        return this;
    }

    public DiceBuilder placeSheep() {
        Animal animal = AnimalFactory.sheep();
        placeAnimal(animal);
        return this;
    }

    public DiceBuilder placePig() {
        Animal animal = AnimalFactory.pig();
        placeAnimal(animal);
        return this;
    }

    public DiceBuilder placeCow() {
        Animal animal = AnimalFactory.cow();
        placeAnimal(animal);
        return this;
    }

    public DiceBuilder placeHorse() {
        Animal animal = AnimalFactory.horse();
        placeAnimal(animal);
        return this;
    }

    public DiceBuilder placeWolf() {
        Animal animal = AnimalFactory.wolf();
        placeAnimal(animal);
        return this;
    }

    public DiceBuilder placeFox() {
        Animal animal = AnimalFactory.fox();
        placeAnimal(animal);
        return this;
    }

}
