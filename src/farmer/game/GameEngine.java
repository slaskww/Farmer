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
        while (true) {
            Field field = playerOne.salvo();
            announcer.announce(PLAYER_SHOOT.event(playerOne, playerTwo));
            ShootResult shootResult = playerTwo.callOut(field);
            announceShootResult(shootResult, playerOne, playerTwo);
            playerOne.use(shootResult, field);


            field = playerTwo.salvo();
            announcer.announce(PLAYER_SHOOT.event(playerTwo, playerOne));
            shootResult  = playerOne.callOut(field);
            announceShootResult(shootResult, playerTwo, playerOne);
            playerTwo.use(shootResult, field);

            if (checkIfFirstPlayerWon()) {
                gameResult = GameResult.FIRST_PLAYER_WON;
                announcer.announce(PLAYER_WIN.event(playerOne, playerTwo));
                return;
            }
            else if (checkIfSecondPlayerWon()) {
                gameResult = GameResult.SECOND_PLAYER_WON;
                announcer.announce(PLAYER_WIN.event(playerTwo, playerOne));
                return;
            }
            else if(checkIfItsDraw()) {
                gameResult = GameResult.DRAW;
                announcer.announce(DRAW.event(playerOne, playerTwo));
                return;
            }
        }
    }

    private void announceShootResult(ShootResult shootResult, Player sourcePlayer, Player targetPlayer) {
        switch (shootResult) {
            case MISS:
                announcer.announce(PLAYER_MISS.event(sourcePlayer, targetPlayer));
                break;
            case HIT:
                announcer.announce(PLAYER_HIT_A_SHIP.event(sourcePlayer, targetPlayer));
                break;
            case HIT_AND_SINK:
                announcer.announce(PLAYER_SANK_A_SHIP.event(sourcePlayer, targetPlayer));
                break;
        }
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    private boolean checkIfItsDraw() {
        return !playerOne.hasMoreShips() && !playerTwo.hasMoreShips();
    }

    private boolean checkIfSecondPlayerWon() {
        return !playerOne.hasMoreShips() && playerTwo.hasMoreShips();
    }

    private boolean checkIfFirstPlayerWon() {
        return playerOne.hasMoreShips() && !playerTwo.hasMoreShips();
    }

    public Announcer getAnnouncer() {
        return announcer;
    }

    public void setAnnouncer(Announcer announcer) {
        this.announcer = announcer;
    }


}
