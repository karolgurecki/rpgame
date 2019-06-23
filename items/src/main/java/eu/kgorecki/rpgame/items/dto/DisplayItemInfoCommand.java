package eu.kgorecki.rpgame.items.dto;

public class DisplayItemInfoCommand {

    private final ItemId itemId;

    private DisplayItemInfoCommand(ItemId itemId) {
        this.itemId = itemId;
    }

    public static DisplayItemInfoCommand of(ItemId itemId) {
        return new DisplayItemInfoCommand(itemId);
    }

    public ItemId getItemId() {
        return itemId;
    }
}
