package farmer.players;

import farmer.animals.Animal;
import farmer.animals.AnimalFactory;
import farmer.animals.FarmAnimal;
import farmer.game.*;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimpleLogic implements PlayerLogic {

    private Board playerBoard;
    private Random random = new Random();
    private Side resultOfRolling = new Side(AnimalFactory.rabbit(), AnimalFactory.rabbit());
    private Dice diceWithWolf = DiceWithWolf.prepareDice();
    private Dice diceWithFox = DiceWithFox.prepareDice();


    public Side rollTheDice() { //the returned value is an object that represents result of rolling two dices

        int returnedSideNumberForFirstDie = random.nextInt(Dice.NUMBER_OF_SIDES_ON_THE_DIE);
        int returnedSideNumberForSecondDie = random.nextInt(Dice.NUMBER_OF_SIDES_ON_THE_DIE);
        Animal firstResultOfRolling = diceWithWolf.getSides().get(returnedSideNumberForFirstDie);
        Animal secondResultOfRolling = diceWithFox.getSides().get(returnedSideNumberForSecondDie);

        resultOfRolling.setResultOfFirtRolling(firstResultOfRolling);
        resultOfRolling.setResultOfSecondRolling(secondResultOfRolling);
       // simulateWaiting();
        return resultOfRolling;
    }


    @Override
    public void use(RollResult result, Side side) {



        if (result == RollResult.WOLF_AND_FOX_KILLED_ANIMALS) { //Case 1: predators killed all farm animals
            Iterator<Pen> iterator = this.playerBoard.penIterator();

            while (iterator.hasNext()) {


              //  System.out.println("value of hasNext(): " + iterator.hasNext());
             //   System.out.println("value of next(): " + iterator.next().getAnimal().getName());
                FarmAnimal animal = iterator.next().getAnimal();

                if (!animal.isResistantToWolves()) {
                    int index = animal.getIndexOfPen();
                    playerBoard.getPens()[index].killAnimals();
                }

                if (!animal.isResistantToFoxes()) {
                    int index = animal.getIndexOfPen();
                    playerBoard.getPens()[index].killAnimals();
                }

            }
            return;
        }

        if (result == RollResult.WOLF_KILLED_ANIMALS_AND_NEW_RABBIT_CAME_TO_THE_WORLD) {
            Iterator<Pen> iterator = this.playerBoard.penIterator();

            while (iterator.hasNext()) {

            //    System.out.println("hasNext()");
           //     System.out.println("value of hasNext(): " + this.playerBoard.penIterator().hasNext());
            //    System.out.println("value of next(): " + this.playerBoard.penIterator().next());

                FarmAnimal animal = iterator.next().getAnimal();
                if (!animal.isResistantToWolves()) {
                    int index = animal.getIndexOfPen();
                    playerBoard.getPens()[index].killAnimals();
                }

            }
            playerBoard.breedAnimals(AnimalFactory.rabbit(), false);
            return;
        }

        if (result == RollResult.WOLF_KILLED_ANIMALS) {
            Iterator<Pen> iterator = this.playerBoard.penIterator();

            while (iterator.hasNext()) {

             //   System.out.println("hasNext()");
            //    System.out.println("value of hasNext(): " + this.playerBoard.penIterator().hasNext());
            //    System.out.println("value of next(): " + iterator.next().getAnimal().getName());
                FarmAnimal animal = iterator.next().getAnimal();

                if (!animal.isResistantToWolves()) {
                    int index = animal.getIndexOfPen();
                    playerBoard.getPens()[index].killAnimals();                }
            }
            return;
        }

        if (result == RollResult.FOX_KILLED_RABBITS) {
            playerBoard.getPens()[((FarmAnimal) side.getResultOfFirstRolling()).getIndexOfPen()].killAnimals(); //TODO
            return;
        }

        if (result == RollResult.FOX_KILLED_RABBITS_AND_NEW_ANIMAL_CAME_TO_THE_WORLD) {

            playerBoard.getPens()[AnimalFactory.rabbit().getIndexOfPen()].killAnimals();
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirstRolling()), false);
            return;
        }

        if (result == RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD_TWICE) {
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirstRolling()), true);
            return;
        }

        if (result == RollResult.NEW_ANIMALS_CAME_TO_THE_WORLD) {
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirstRolling()), false);
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfSecondRolling()), false);
            return;
        }

        if (result == RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD) {

            if (playerBoard.isAnyAnimalInPen((FarmAnimal) side.getResultOfFirstRolling())) {
                playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirstRolling()), false);
            } else {
                playerBoard.breedAnimals(((FarmAnimal) side.getResultOfSecondRolling()), false);
            }
            return;
        }

        if (result == RollResult.NO_CHANGES_ON_THE_FARM){}
    }

    public void swapXForYIfPossible(){
        Iterator<Pen> iterator = playerBoard.penIterator();
        FarmAnimal animal;

        while(iterator.hasNext()) {
            animal = iterator.next().getAnimal();
         //   System.out.println("in swapXForY in SimpleLogic, next animal: " + animal.getName());
            playerBoard.swapXForY(animal);
        }
    }

    @Override
    public RollResult callOut(Side side) {

        Animal resultOfFirstRolling = side.getResultOfFirstRolling();
        Animal resultOfSecondRolling = side.getResultOfSecondRolling();
      //  System.out.println("Inside callout():");
     //   System.out.format("First: %s Second: %s ", resultOfFirstRolling.getName(), resultOfSecondRolling.getName());
     //   System.out.println();

        if (resultOfFirstRolling.getName().equals("Wolf") && resultOfSecondRolling.getName().equals("Fox")) { //wolf & fox
        //    System.out.println("callout1");
            return RollResult.WOLF_AND_FOX_KILLED_ANIMALS;

        }

        if (resultOfFirstRolling.getName().equals("Wolf") && resultOfSecondRolling.getName().equals("Rabbit")) { //wolf & rabbit
         //   System.out.println("callout2");
            return RollResult.WOLF_KILLED_ANIMALS_AND_NEW_RABBIT_CAME_TO_THE_WORLD;
        }

        if (resultOfFirstRolling.getName().equals("Wolf") && !resultOfSecondRolling.getName().equals("Rabbit")) { //wolf & other farm animal
          //  System.out.println("callout3");
            return RollResult.WOLF_KILLED_ANIMALS;
        }

        if (resultOfFirstRolling.getName().equals("Rabbit") && resultOfSecondRolling.getName().equals("Fox")) { //rabbit & fox
          //  System.out.println("callout4");
            return RollResult.FOX_KILLED_RABBITS;
        }

        if (!resultOfFirstRolling.getName().equals("Rabbit") && resultOfSecondRolling.getName().equals("Fox")) { //other farm animal  & fox
        //    System.out.println("callout5");
            return playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfFirstRolling)
                    ? RollResult.FOX_KILLED_RABBITS_AND_NEW_ANIMAL_CAME_TO_THE_WORLD : RollResult.FOX_KILLED_RABBITS;
        }

        if (resultOfFirstRolling.getName().equals(resultOfSecondRolling.getName())) { //the same species on both dices
        //    System.out.println("callout6");
            return RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD_TWICE;
        }

        if (playerBoard.isAnyAnimalInPen((FarmAnimal) side.getResultOfFirstRolling()) && playerBoard.isAnyAnimalInPen((FarmAnimal) side.getResultOfSecondRolling())) { // farm animal when pen is not empty  & farm animal when pen is not empty
         //   System.out.println("callout7");
         //   System.out.println("warunek1: " + playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfFirstRolling));
         //   System.out.println("warunek2: " + playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfSecondRolling));

            return RollResult.NEW_ANIMALS_CAME_TO_THE_WORLD;
        }

        if (playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfFirstRolling) || playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfSecondRolling)) { // farm animal when one of two pens is not empty
        //    System.out.println("callout8");
            return RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD;
        }
     //   System.out.println("callout9");
        return RollResult.NO_CHANGES_ON_THE_FARM; //default result
    }

    @Override
    public Board prepareBoard() {
        BoardBuilder builder = new BoardBuilder();

        builder
                .rabbitsPen()
                .sheepsPen()
                .pigsPen()
                .cowsPen()
                .horsesPen();

        Board board = builder.build();
        playerBoard = board;

        return board;
    }

    public void simulateWaiting() {

        int time = 500 + random.nextInt(500);

        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

