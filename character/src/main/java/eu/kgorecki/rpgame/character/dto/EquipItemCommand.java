package eu.kgorecki.rpgame.character.dto;

import eu.kgorecki.rpgame.items.dto.ItemId;

public class EquipItemCommand {
    private final CharacterId characterId;
    private final ItemId itemId;

    public EquipItemCommand(CharacterId characterId, ItemId itemId) {
        this.characterId = characterId;
        this.itemId = itemId;
    }

    public CharacterId getCharacterId() {
        return characterId;
    }

    public ItemId getItemId() {
        return itemId;
    }
}
