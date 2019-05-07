package farmer.game;

public class DiceWithFox extends Dice {

    public DiceWithFox() {
    }

    public static Dice prepareDice(){
        DiceBuilder builder = new DiceBuilder();

        builder
                .placeRabbit()
                .placeRabbit()
                .placeRabbit()
                .placeRabbit()
                .placeRabbit()
                .placeRabbit()
                .placeSheep()
                .placeSheep()
                .placePig()
                .placePig()
                .placeHorse()
                .placeFox();

        Dice dice = builder.build();
        return dice;
    }
}
