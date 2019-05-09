package farmer;

import farmer.game.GameEngine;
import farmer.game.GameResult;
import farmer.players.Player;
import farmer.players.SimpleLogic;

public class FarmerApp {

    public static void main(String[] args) {

        Player player1 = new Player(new SimpleLogic());
        player1.setName("Basketor");
        Player player2 = new Player(new SimpleLogic());
        player2.setName("Greyfus");

        GameEngine gameEngine = new GameEngine();
        gameEngine.prepareGame(player1, player2);
        gameEngine.prepareBoards();
        gameEngine.startGame();
    }


}
