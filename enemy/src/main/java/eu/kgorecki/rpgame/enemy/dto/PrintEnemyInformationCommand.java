package eu.kgorecki.rpgame.enemy.dto;

public class PrintEnemyInformationCommand {

    private final EnemyId enemyId;

    public PrintEnemyInformationCommand(EnemyId enemyId) {
        this.enemyId = enemyId;
    }

    public EnemyId getEnemyId() {
        return enemyId;
    }
}
