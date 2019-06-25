package eu.kgorecki.rpgame.enemy.dto;

public class EnemyTakeDamageCommand {
    private final EnemyId enemyId;
    private final int attackPower;

    public EnemyTakeDamageCommand(EnemyId enemyId, int attackPower) {
        this.enemyId = enemyId;
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public EnemyId getEnemyId() {
        return enemyId;
    }
}
