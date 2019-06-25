package eu.kgorecki.rpgame.enemy.dto;

public class EnemyAttackPowerQuery {
    private final EnemyId enemyId;

    public EnemyAttackPowerQuery(EnemyId enemyId) {
        this.enemyId = enemyId;
    }

    public EnemyId getEnemyId() {
        return enemyId;
    }
}
