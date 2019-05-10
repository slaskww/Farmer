package farmer.game;

import farmer.players.Player;

public class GameEngine {

    private Player playerOne;
    private Player playerTwo;
    private GameResult gameResult = GameResult.NOT_FINISHED_YET;

    public void prepareGame(Player p1, Player p2) {
        gameResult = GameResult.NOT_FINISHED_YET;
        this.playerOne = p1;
        this.playerTwo = p2;
    }

    public void prepareBoards() {
        playerOne.prepareBoard();
        playerTwo.prepareBoard();
    }

    public void startGame() {

        System.out.println("Before start:");
        playerOne.getBoard().showPens();
        playerOne.getBoard().showPens();
        System.out.println();

        int counter = 0;
        while(true){
            counter++;
            System.out.println("#" + counter);
            System.out.println("player1:");
            Side side = playerOne.rollTheDice();
            RollResult rollResult = playerOne.callOut(side);
            System.out.println(rollResult);
            playerOne.use(rollResult, side);
            System.out.println(side.getResultOfFirstRolling().getName());
            System.out.println(side.getResultOfSecondRolling().getName());
            playerOne.getBoard().showPens();
            playerOne.swapXForYIfPossible();

            System.out.println();
            System.out.println();
            System.out.println("player2:");
            side = playerTwo.rollTheDice();
            rollResult = playerTwo.callOut(side);
            System.out.println(rollResult);
            playerTwo.use(rollResult, side);
            System.out.println(side.getResultOfFirstRolling().getName());
            System.out.println(side.getResultOfSecondRolling().getName());
            playerTwo.getBoard().showPens();
            playerTwo.swapXForYIfPossible();
            System.out.println();


            if (checkIfFirstPlayerWon()){
                gameResult = GameResult.FIRST_PLAYER_WON;
                System.out.println("player " + playerOne.getName() + " won!");
                return;
            } else if (checkIfSecondPlayerWon()) {
                gameResult = GameResult.SECOND_PLAYER_WON;
                System.out.println("player " + playerTwo.getName() + " won!");
                return;
            } else{
                gameResult = GameResult.NOT_FINISHED_YET;
                System.out.println("Not finished yet!");
            }

        }


    }


    public GameResult getGameResult() {
        return gameResult;
    }

    private boolean checkIfSecondPlayerWon() {
        return  playerTwo.hasFourHorses();
    }

    private boolean checkIfFirstPlayerWon() {
        return playerOne.hasFourHorses();
    }



}
