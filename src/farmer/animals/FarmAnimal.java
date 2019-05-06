package farmer.animals;

public class FarmAnimal extends Animal {

   private boolean isresistantToWolves;
   private boolean isResistantToFoxes;


    public FarmAnimal(String name, boolean isresistantToWolves ,boolean isResistantToFoxes) {
        super(name);
        this.isResistantToFoxes = isResistantToFoxes;
        this.isresistantToWolves = isresistantToWolves;
    }

    public boolean isIsresistantToWolves() {
        return isresistantToWolves;
    }

    public boolean isResistantToFoxes() {
        return isResistantToFoxes;
    }
}
