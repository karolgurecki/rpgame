package eu.kgorecki.rpgame.items.domain;

import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.items.dto.ItemStatistics;
import eu.kgorecki.rpgame.items.dto.ItemType;

public class Item {
    private final Id id;
    private final String name;
    private final Type type;
    private final int attackModifier;
    private final int defenceModifier;

    private Item(Id id, String name, Type type, int attackModifier, int defenceModifier) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attackModifier = attackModifier;
        this.defenceModifier = defenceModifier;
    }

    public static Item weaponOf(int id, String name, int attackModifier) {
        return new Item(Id.of(id), name, Type.WEAPON, attackModifier, 0);
    }

    public static Item shieldOf(int id, String name, int defenceModifier) {
        return new Item(Id.of(id), name, Type.SHIELD, 0, defenceModifier);
    }

    public Id getId() {
        return id;
    }

    boolean isWeapon() {
        return type == Type.WEAPON;
    }

    boolean isShield() {
        return type == Type.SHIELD;
    }

    ItemId getIdAsItemId() {
        return ItemId.of(id.getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Item ");

        sb.append("name='").append(name).append('\'');
        sb.append(", type=").append(type);
        sb.append(", attackModifier=").append(attackModifier);
        sb.append(", defenceModifier=").append(defenceModifier);

        return sb.toString();
    }

    ItemStatistics getItemStatistics() {
        return new ItemStatistics(getIdAsItemId(), ItemType.valueOf(type.name()), attackModifier, defenceModifier);
    }
}
