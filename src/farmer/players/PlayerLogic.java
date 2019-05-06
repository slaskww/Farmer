package farmer.players;

import farmer.game.Board;
import farmer.game.RollResult;
import farmer.game.Side;

public interface PlayerLogic {

    Side rollTheDice();

    RollResult callOut(Side side);

    default void use(RollResult result, Side side) {}

    Board prepareBoard();


}
