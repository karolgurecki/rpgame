package eu.kgorecki.rpgame.enemy.dto;

public class EnemyStatusQuery {
    private final EnemyId enemyId;

    public EnemyStatusQuery(EnemyId enemyId) {
        this.enemyId = enemyId;
    }

    public EnemyId getEnemyId() {
        return enemyId;
    }
}
