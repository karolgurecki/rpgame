package eu.kgorecki.rpgame.engine.domain;

public class Service {

    private static final String INTRO = "RPG game by Karol Górecki. 2019 \n" +
            "Type \"list command\" for list of available commands or \"create character\" to create character and start the game.";
    private final CommandPort commandPort;
    private final EnemyActionPort enemyActionPort;
    private final UserInteractionPort userInteractionPort;
    private boolean runTheGame = true;

    public Service(CommandPort commandPort, EnemyActionPort enemyActionPort, UserInteractionPort userInteractionPort) {
        this.commandPort = commandPort;
        this.enemyActionPort = enemyActionPort;
        this.userInteractionPort = userInteractionPort;
    }

    public void runGame() {
        userInteractionPort.displayText(INTRO);

        while (runTheGame) {
            String userCommand = commandPort.read();
            commandPort.execute(userCommand);
            enemyTurn();
        }
    }

    private void enemyTurn() {
        enemyActionPort.executeEnemyTurn();
    }
}
