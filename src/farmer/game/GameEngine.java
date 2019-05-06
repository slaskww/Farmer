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
