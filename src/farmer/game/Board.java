package farmer.game;

import farmer.animals.FarmAnimal;

import java.util.Iterator;

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

    public boolean  isAnyAnimalInPen(FarmAnimal farmAnimal){
        return pens[farmAnimal.getIndexOfPen()].getSize() > 0;
    }

    public boolean hasFourHorses(){
        return pens[4].getSize() > 3;
    }

    public void swapXForY(FarmAnimal animalToSwap){ //this method swaps one species to another when the amount of flock is excessive

        if (animalToSwap.getName() == "Rabbit"){ // 6 rabbits = 1 sheep
           swap(animalToSwap, 6);
        }

        if (animalToSwap.getName() == "Sheep"){ // 2 sheep = 1 pig
            swap(animalToSwap, 2);
        }

        if (animalToSwap.getName() == "Pig"){ // 3 pigs = 1 cow
            swap(animalToSwap, 3);
        }

        if (animalToSwap.getName() == "Cow"){ // 2 cows = 1 horse
            swap(animalToSwap, 2);
        }

        if (animalToSwap.getName() == "Horse"){ // horses are not swappable

        }

    }

    private void swap(FarmAnimal animal, int conversionRate){
        int currentNumberOfAnimalsX = pens[animal.getIndexOfPen()].getSize();
        int numberOfAnimalsYAsAResultOfSwapping = currentNumberOfAnimalsX % conversionRate;
        int animalsXToRemoveAfterSwapping = numberOfAnimalsYAsAResultOfSwapping * conversionRate;
        pens[animal.getIndexOfPen()].removeAnimal(animalsXToRemoveAfterSwapping); //pen X update
        pens[animal.getIndexOfPen() + 1].addAnimal(numberOfAnimalsYAsAResultOfSwapping); //pen Y update
    }


    public Iterator<Pen> penIterator(){ //implements design pattern: Iterator
        return new PensIterator(this);
    }

}
