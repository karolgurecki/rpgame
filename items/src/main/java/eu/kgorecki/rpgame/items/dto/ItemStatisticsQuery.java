package eu.kgorecki.rpgame.items.dto;

public class ItemStatisticsQuery {

    private final ItemId itemId;

    private ItemStatisticsQuery(ItemId itemId) {
        this.itemId = itemId;
    }

    public static ItemStatisticsQuery of(ItemId itemId) {
        return new ItemStatisticsQuery(itemId);
    }

    public ItemId getItemId() {
        return itemId;
    }
}
