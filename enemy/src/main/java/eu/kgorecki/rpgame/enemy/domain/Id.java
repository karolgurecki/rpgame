package eu.kgorecki.rpgame.enemy.domain;

import eu.kgorecki.rpgame.enemy.dto.EnemyId;

import java.io.Serializable;
import java.util.Objects;

public class Id implements Serializable {
    private final int id;

    Id(int id) {
        this.id = id;
    }

    public static Id of(EnemyId id) {
        return new Id(id.getId());
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id1 = (Id) o;
        return id == id1.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
