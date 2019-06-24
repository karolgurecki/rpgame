package eu.kgorecki.rpgame.character.domain;


import java.util.Objects;
import java.util.UUID;

public class Id {
    private final UUID id;

    private Id(UUID id) {
        this.id = id;
    }

    public static Id generateId() {
        return new Id(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id1 = (Id) o;
        return Objects.equals(id, id1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
