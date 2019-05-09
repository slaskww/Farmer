package farmer.animals;

public class FarmAnimal extends Animal {

   private boolean isresistantToWolves;
   private boolean isResistantToFoxes;
   private int indexOfPen;


    public FarmAnimal(String name, boolean isresistantToWolves ,boolean isResistantToFoxes, int indexOfPen) {
        super(name);
        this.isResistantToFoxes = isResistantToFoxes;
        this.isresistantToWolves = isresistantToWolves;
    }

    public boolean isResistantToWolves() {
        return isresistantToWolves;
    }

    public boolean isResistantToFoxes() {
        return isResistantToFoxes;
    }

    public int getIndexOfPen() {
        return indexOfPen;
    }
}
