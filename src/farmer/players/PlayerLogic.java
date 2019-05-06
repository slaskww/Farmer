package farmer.players;

public interface PlayerLogic {

    Dice rollTheDice();

    rollResult callOut(Dice dice);

    default void use(rollResult result, Field field) {}

    Board prepareBoard();


}
