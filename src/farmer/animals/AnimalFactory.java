package farmer.animals;

public class AnimalFactory {

    public static FarmAnimal rabbit() {
        FarmAnimal animal = new FarmAnimal("Rabbit", true, false);
        return animal;
    }

    public static FarmAnimal sheep() {
        FarmAnimal animal = new FarmAnimal("Sheep", false, true);
        return animal;
    }

    public static FarmAnimal pig() {
        FarmAnimal animal = new FarmAnimal("Pig", false, true);
        return animal;
    }

    public static FarmAnimal cow() {
        FarmAnimal animal = new FarmAnimal("Cow", false, true);
        return animal;
    }

    public static FarmAnimal horse() {
        FarmAnimal animal = new FarmAnimal("Horse", true, true);
        return animal;
    }

}
