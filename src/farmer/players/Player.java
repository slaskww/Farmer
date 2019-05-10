package farmer.players;

import farmer.game.Board;
import farmer.game.RollResult;
import farmer.game.Side;

public class Player {

    private String name;
    private Board board;
    protected PlayerLogic logic;

    public Player(PlayerLogic logic) {
        this.logic = logic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Board getBoard() {
        return this.board;
    }

    public Side rollTheDice() {
        return logic.rollTheDice();
    }

    public RollResult callOut(Side side) {
        return logic.callOut(side);
    }

    public void swapXForYIfPossible(){
        this.logic.swapXForYIfPossible();
    }

    public void use(RollResult result, Side side) {
        logic.use(result, side);
    }

    public void prepareBoard() {
        this.board = logic.prepareBoard();
    }



    public boolean hasFourHorses() { //bridge
        return board.hasFourHorses();
    }
}
