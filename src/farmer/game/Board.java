package farmer.game;

import farmer.animals.AnimalFactory;
import farmer.animals.FarmAnimal;

import java.util.Iterator;

public class Board {

    private final Pen[] pens;
    private static final int SIZE = 5; //rabbit, sheep, pig, cow, horse

    public Board() {
        this.pens = new Pen[SIZE];
        pens[0] = new Pen(AnimalFactory.rabbit(), 1);
        pens[1] = new Pen(AnimalFactory.sheep(), 0);
        pens[2] = new Pen(AnimalFactory.pig(), 0);
        pens[3] = new Pen(AnimalFactory.cow(), 0);
        pens[4] = new Pen(AnimalFactory.horse(), 0);

    }


    public void showPens(){
        System.out.format("R:%d S:%d P:%d C:%d H:%d",pens[0].getSize(), pens[1].getSize(), pens[2].getSize(), pens[3].getSize(), pens[4].getSize());
        System.out.println();
    }

    public Pen[] getPens() {
        return pens;
    }

    public void setPen(Pen pen, int index) {

    }

    public boolean isAnyAnimalInPen(FarmAnimal farmAnimal) {

        return !pens[farmAnimal.getIndexOfPen()].isPenEmpty();
    }

    public boolean hasFourHorses() {
        return pens[4].getSize() > 3;
    }

    public void swapXForY(FarmAnimal animalToSwap) { //this method swaps one species to another when the amount of flock is excessive

        if (animalToSwap.getName() == "Rabbit") { // 6 rabbits = 1 sheep
            swap(animalToSwap, 6);
        }

        if (animalToSwap.getName() == "Sheep") { // 2 sheep = 1 pig
            swap(animalToSwap, 2);
        }

        if (animalToSwap.getName() == "Pig") { // 3 pigs = 1 cow
            swap(animalToSwap, 3);
        }

        if (animalToSwap.getName() == "Cow") { // 2 cows = 1 horse
            swap(animalToSwap, 2);
        }

        if (animalToSwap.getName() == "Horse") { // horses are not swappable

        }

    }

    private void swap(FarmAnimal animal, int conversionRate) {
        int currentNumberOfAnimalsX = pens[animal.getIndexOfPen()].getSize();

        if (currentNumberOfAnimalsX >= conversionRate) {
        int numberOfAnimalsYAsAResultOfSwapping = currentNumberOfAnimalsX / conversionRate;
        int animalsXToRemoveAfterSwapping = numberOfAnimalsYAsAResultOfSwapping * conversionRate;
        //    System.out.println("In swap(), Board, animals initially: " + currentNumberOfAnimalsX);
        //    System.out.println("In swap(), Board, animals to remove: " + animalsXToRemoveAfterSwapping);
        //   System.out.println("In swap(), Board, animals to add: " + numberOfAnimalsYAsAResultOfSwapping);
            pens[animal.getIndexOfPen()].removeAnimal(animalsXToRemoveAfterSwapping); //pen X update
            pens[animal.getIndexOfPen() + 1].addAnimal(numberOfAnimalsYAsAResultOfSwapping); //pen Y update
            System.out.println(animal.getName() + "(" + animalsXToRemoveAfterSwapping + ") -> " + pens[animal.getIndexOfPen()+1].getAnimal().getName() + "(" + numberOfAnimalsYAsAResultOfSwapping + ")");
        }
    }


    public Iterator<Pen> penIterator() { //implements design pattern: Iterator
        return new PensIterator(this);
    }


    public void breedAnimals(FarmAnimal animal, boolean isDrawnOnBothDice) {

        pens[animal.getIndexOfPen()].breedAnimals(isDrawnOnBothDice);


    } //this method multiply species given in the parameter, parameter isDrawnOnBothDice has 'true' value when the same species was drawn on both dice


}
