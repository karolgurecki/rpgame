package eu.kgorecki.rpgame.world.dto;

import eu.kgorecki.rpgame.character.dto.CharacterId;

public class CreateWorldCommand {
    private final CharacterId characterId;

    public CreateWorldCommand(CharacterId characterId) {
        this.characterId = characterId;
    }

    public CharacterId getCharacterId() {
        return characterId;
    }
}
