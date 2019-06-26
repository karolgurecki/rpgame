package eu.kgorecki.rpgame.character.dto;

import java.util.Objects;

public class CharacterAttackPowerQuery {
    private final CharacterId characterId;

    public CharacterAttackPowerQuery(CharacterId characterId) {
        this.characterId = characterId;
    }

    public CharacterId getCharacterId() {
        return characterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterAttackPowerQuery that = (CharacterAttackPowerQuery) o;
        return Objects.equals(characterId, that.characterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterId);
    }
}
