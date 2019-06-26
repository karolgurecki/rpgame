package eu.kgorecki.rpgame.engine.domain;

public class Service {

    private final CommandPort commandPort;
    private final EnemyActionPort enemyActionPort;
    private boolean runTheGame = true;

    public Service(CommandPort commandPort, EnemyActionPort enemyActionPort) {
        this.commandPort = commandPort;
        this.enemyActionPort = enemyActionPort;
    }

    public void runGame() {
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
