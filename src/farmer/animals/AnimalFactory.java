package farmer.animals;

public class AnimalFactory {

    public static FarmAnimal rabbit() {
        FarmAnimal animal = new FarmAnimal("Rabbit", true, false, 0);
        return animal;
    }

    public static FarmAnimal sheep() {
        FarmAnimal animal = new FarmAnimal("Sheep", false, true, 1);
        return animal;
    }

    public static FarmAnimal pig() {
        FarmAnimal animal = new FarmAnimal("Pig", false, true, 2);
        return animal;
    }

    public static FarmAnimal cow() {
        FarmAnimal animal = new FarmAnimal("Cow", false, true, 3);
        return animal;
    }

    public static FarmAnimal horse() {
        FarmAnimal animal = new FarmAnimal("Horse", true, true, 4);
        return animal;
    }

    public static Predator wolf() {
        Predator animal = new Predator("Wolf");
        return animal;
    }

    public static Predator fox() {
        Predator animal = new Predator("Fox");
        return animal;
    }

}
