package eu.kgorecki.rpgame.enemy.dto;

public class PringEnemyInformationCommand {

    private final EnemyId enemyId;

    public PringEnemyInformationCommand(EnemyId enemyId) {
        this.enemyId = enemyId;
    }

    public EnemyId getEnemyId() {
        return enemyId;
    }
}
