package farmer.game;

public class DiceWithWolf extends Dice {


    public DiceWithWolf() {
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
                .placeCow()
                .placeWolf();

        Dice dice = builder.build();
        return dice;
    }
}
