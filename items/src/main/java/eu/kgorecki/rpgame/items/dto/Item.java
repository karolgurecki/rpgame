package eu.kgorecki.rpgame.items.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private final String name;
    private final ItemType type;
    private final BigDecimal attackModifier;
    private final BigDecimal defenceModifier;

    private Item(String name, ItemType type, BigDecimal attackModifier, BigDecimal defenceModifier) {
        this.name = name;
        this.type = type;
        this.attackModifier = attackModifier;
        this.defenceModifier = defenceModifier;
    }

    public static Item weaponOf(String name, BigDecimal attackModifier) {
        return new Item(name, ItemType.WEAPON, attackModifier, BigDecimal.ZERO);
    }

    public static Item shieldOf(String name, BigDecimal attackModifier) {
        return new Item(name, ItemType.SHIELD, BigDecimal.ZERO, attackModifier);
    }

    public String getName() {
        return name;
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
        Item item = (Item) o;
        return type == item.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
