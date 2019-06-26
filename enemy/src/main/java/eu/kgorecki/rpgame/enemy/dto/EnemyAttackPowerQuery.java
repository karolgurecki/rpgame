package eu.kgorecki.rpgame.enemy.dto;

import java.util.Objects;

public class EnemyAttackPowerQuery {
    private final EnemyId enemyId;

    public EnemyAttackPowerQuery(EnemyId enemyId) {
        this.enemyId = enemyId;
    }

    public EnemyId getEnemyId() {
        return enemyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyAttackPowerQuery that = (EnemyAttackPowerQuery) o;
        return Objects.equals(enemyId, that.enemyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enemyId);
    }

}
