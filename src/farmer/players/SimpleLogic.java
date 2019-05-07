package farmer.players;

import farmer.game.Board;
import farmer.game.BoardBuilder;
import farmer.game.RollResult;
import farmer.game.Side;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SimpleLogic implements PlayerLogic {

    private Board playerBoard;
    private Random random = new Random();

    public Side rollTheDice() {
        return null;
    }

    public RollResult callOut(Side side) {
        return null;
    }


    public Board prepareBoard() {
        BoardBuilder builder = new BoardBuilder();

        builder
                .rabbitsPen()
                .sheepsPen()
                .pigsPen()
                .cowsPen()
                .horsesPen();

        Board board =  builder.build();
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

