package eu.kgorecki.rpgame.items.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemStatistics {
    private final ItemId id;
    private final ItemType type;
    private final BigDecimal attackModifier;
    private final BigDecimal defenceModifier;

    public ItemStatistics(ItemId id, ItemType type, BigDecimal attackModifier, BigDecimal defenceModifier) {
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

    public BigDecimal getAttackModifier() {
        return attackModifier;
    }

    public BigDecimal getDefenceModifier() {
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
