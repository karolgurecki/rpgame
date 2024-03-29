package eu.kgorecki.rpgame.items.dto;

import java.io.Serializable;
import java.util.Objects;

public class ItemId implements Serializable {
    private final int id;

    public ItemId(int id) {
        this.id = id;
    }

    public static ItemId of(int id) {
        return new ItemId(id);
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemId itemId = (ItemId) o;
        return id == itemId.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
