package eu.kgorecki.rpgame.enemy.dto;

import java.util.Objects;

public class EnemyId {
    private final int id;

    private EnemyId(int id) {
        this.id = id;
    }

    public static EnemyId of(int id) {
        return new EnemyId(id);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyId enemyId = (EnemyId) o;
        return id == enemyId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
