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

        int counter = 0;
        while(true){
            counter++;
            System.out.println("#" + counter);

            Side side = playerOne.rollTheDice();
            RollResult rollResult = playerOne.callOut(side);
            playerOne.use(rollResult, side);

            side = playerTwo.rollTheDice();
            rollResult = playerTwo.callOut(side);
            playerTwo.use(rollResult, side);


            if (checkIfFirstPlayerWon()){
                gameResult = GameResult.FIRST_PLAYER_WON;
                System.out.println("player " + playerOne.getName() + " won!");
            } else if (checkIfSecondPlayerWon()) {
                gameResult = GameResult.SECOND_PLAYER_WON;
                System.out.println("player " + playerTwo.getName() + " won!");
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
