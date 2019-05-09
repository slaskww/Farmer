package farmer.players;

import farmer.animals.Animal;
import farmer.animals.AnimalFactory;
import farmer.animals.FarmAnimal;
import farmer.game.*;

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

        return resultOfRolling;
    }


    @Override
    public void use(RollResult result, Side side) {

        if (result == RollResult.WOLF_AND_FOX_KILLED_ANIMALS) { //Case 1: predators killed all farm animals
            while (playerBoard.penIterator().hasNext()) {

                if (!playerBoard.penIterator().next().getAnimal().isResistantToWolves()) {
                    playerBoard.penIterator().next().killAnimals();
                }

                if (!playerBoard.penIterator().next().getAnimal().isResistantToFoxes()) {
                    playerBoard.penIterator().next().killAnimals();
                }
            }
        }

        if (result == RollResult.WOLF_KILLED_ANIMALS_AND_NEW_RABBIT_CAME_TO_THE_WORLD) {
            while (playerBoard.penIterator().hasNext()) {

                if (!playerBoard.penIterator().next().getAnimal().isResistantToWolves()) {
                    playerBoard.penIterator().next().killAnimals();
                }
            }
            playerBoard.breedAnimals(AnimalFactory.rabbit(), false);
        }

        if (result == RollResult.WOLF_KILLED_ANIMALS) {
            while (playerBoard.penIterator().hasNext()) {

                if (!playerBoard.penIterator().next().getAnimal().isResistantToWolves()) {
                    playerBoard.penIterator().next().killAnimals();
                }
            }
        }

        if (result == RollResult.FOX_KILLED_RABBITS) {
            playerBoard.getPens()[((FarmAnimal) side.getResultOfFirtRolling()).getIndexOfPen()].killAnimals(); //TODO
        }

        if (result == RollResult.FOX_KILLED_RABBITS_AND_NEW_ANIMAL_CAME_TO_THE_WORLD) {

            playerBoard.getPens()[AnimalFactory.rabbit().getIndexOfPen()].killAnimals();
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirtRolling()), false);

        }

        if (result == RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD_TWICE) {
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirtRolling()), true);
        }

        if (result == RollResult.NEW_ANIMALS_CAME_TO_THE_WORLD) {
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirtRolling()), false);
            playerBoard.breedAnimals(((FarmAnimal) side.getResultOfSecondRolling()), false);
        }

        if (result == RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD) {

            if (playerBoard.isAnyAnimalInPen((FarmAnimal) side.getResultOfFirtRolling())) {
                playerBoard.breedAnimals(((FarmAnimal) side.getResultOfFirtRolling()), false);
            } else {
                playerBoard.breedAnimals(((FarmAnimal) side.getResultOfSecondRolling()), false);
            }
        }

        if (result == RollResult.NO_CHANGES_ON_THE_FARM){}
    }

    public RollResult callOut(Side side) {

        Animal resultOfFirstRolling = side.getResultOfFirtRolling();
        Animal resultOfSecondRolling = side.getResultOfSecondRolling();

        if (resultOfFirstRolling.getName() == "Wolf" && resultOfSecondRolling.getName() == "Fox") { //wolf & fox
            return RollResult.WOLF_AND_FOX_KILLED_ANIMALS;
        }

        if (resultOfFirstRolling.getName() == "Wolf" && resultOfSecondRolling.getName() == "Rabbit") { //wolf & rabbit
            return RollResult.WOLF_KILLED_ANIMALS_AND_NEW_RABBIT_CAME_TO_THE_WORLD;
        }

        if (resultOfFirstRolling.getName() == "Wolf" && resultOfSecondRolling.getName() != "Rabbit") { //wolf & other farm animal
            return RollResult.WOLF_KILLED_ANIMALS;
        }

        if (resultOfFirstRolling.getName() == "Rabbit" && resultOfSecondRolling.getName() == "Fox") { //rabbit & fox
            return RollResult.FOX_KILLED_RABBITS;
        }

        if (resultOfFirstRolling.getName() != "Rabbit" && resultOfSecondRolling.getName() == "Fox") { //other farm animal  & fox
            return playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfFirstRolling)
                    ? RollResult.FOX_KILLED_RABBITS_AND_NEW_ANIMAL_CAME_TO_THE_WORLD : RollResult.FOX_KILLED_RABBITS;
        }

        if (resultOfFirstRolling.getName() == resultOfSecondRolling.getName()) { //the same species on both dices
            return RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD_TWICE;
        }

        if (playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfFirstRolling) && playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfSecondRolling)) { // farm animal when pen is not empty  & farm animal when pen is not empty
            return RollResult.NEW_ANIMALS_CAME_TO_THE_WORLD;
        }

        if (playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfFirstRolling) || playerBoard.isAnyAnimalInPen((FarmAnimal) resultOfSecondRolling)) { // farm animal when one of two pens is not empty
            return RollResult.NEW_ANIMAL_CAME_TO_THE_WORLD;
        }

        return RollResult.NO_CHANGES_ON_THE_FARM; //default result
    }


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

