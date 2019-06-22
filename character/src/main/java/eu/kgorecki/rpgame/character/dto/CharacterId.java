package eu.kgorecki.rpgame.character.dto;

import java.util.Objects;
import java.util.UUID;

public class CharacterId {
    private final UUID id;

    private CharacterId(UUID id) {
        this.id = id;
    }

    public static CharacterId of(UUID id) {
        return new CharacterId(id);
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterId chacterId = (CharacterId) o;
        return Objects.equals(id, chacterId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
