package eu.kgorecki.rpgame.items.dto;

import java.util.Objects;

public class ItemStatistics {
    private final ItemId id;
    private final ItemType type;
    private final int attackModifier;
    private final int defenceModifier;

    public ItemStatistics(ItemId id, ItemType type, int attackModifier, int defenceModifier) {
        this.id = id;
        this.type = type;
        this.attackModifier = attackModifier;
        this.defenceModifier = defenceModifier;
    }

    public ItemId getId() {
        return id;
    }

    public ItemType getType() {
        return type;
    }

    public int getAttackModifier() {
        return attackModifier;
    }

    public int getDefenceModifier() {
        return defenceModifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemStatistics itemStats = (ItemStatistics) o;
        return Objects.equals(id, itemStats.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
