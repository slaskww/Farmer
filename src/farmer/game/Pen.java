package farmer.game;

import farmer.animals.FarmAnimal;

public class Pen { //  separated part of the farm where each type of animals are kept

    private FarmAnimal animal;
    private int size;
    public static final int NUMBER_OF_ANIMALS_AFTER_A_WOLF_ATTACK = 0;
    public static final int NUMBER_OF_RABBITS_AFTER_A_FOX_ATTACK = 1; //one rabbit always survives a fox attack
    public final int BREEDING_RATE = 2;
    public final int BENEFIT_FOR_THE_SAME_RESULT_ON_BOTH_DICE = 1;


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

    public boolean isPenEmpty(){
        return this.size == 0;
    }

    public void addAnimal(int number) {

        if (number < 0) {
            throw new IllegalArgumentException("This value cannot be negative");
        }
        this.size += number;
    }

    public void removeAnimal(int number) {

        if (number > this.size) {
            throw new IllegalArgumentException("This value exceeds the size of pen");
        }

        if (number < 0) {
            throw new IllegalArgumentException("This value cannot be negative");
        }

        this.size -= number;

        if (animal.getName() == "Rabbit" && this.size == 0){
            this.size = 1;
        }
    }

    public void killAnimals() {
        this.size = this.animal.getName() == "Rabbit" ? NUMBER_OF_RABBITS_AFTER_A_FOX_ATTACK : NUMBER_OF_ANIMALS_AFTER_A_WOLF_ATTACK;
    }

    public void breedAnimals(boolean drawnOnBothDice) {

        int currentNumberOfAnimalsInPen = size;
        int numberOfNewAnimalsInPen = currentNumberOfAnimalsInPen / BREEDING_RATE; //each pair of animals gives birth to one animal

        if (drawnOnBothDice) { //Case 1: the same species was drawn on both dice, so we get one extra example of animal for it
            size += numberOfNewAnimalsInPen + BENEFIT_FOR_THE_SAME_RESULT_ON_BOTH_DICE;
        } else { //Case 2: different species were drawn on both dice, so we don't get any benefits
            size += numberOfNewAnimalsInPen;
        }

    }
    }
