package eu.kgorecki.rpgame.character.infrastructure;

import eu.kgorecki.rpgame.character.domain.Character;
import eu.kgorecki.rpgame.character.domain.RepositoryPort;
import eu.kgorecki.rpgame.character.dto.CharacterId;

import java.util.Objects;
import java.util.Optional;

public class SingleCharacterRepositoryAdapter implements RepositoryPort {

    private Character character;
    private CharacterId characterId;

    @Override
    public void save(Character character) {
        this.character = character;
        this.characterId = character.getIdAsCharacterId();
    }

    @Override
    public Optional<Character> findById(CharacterId id) {
        return Objects.nonNull(characterId) && characterId.equals(id) ? Optional.of(character) : Optional.empty();
    }

    @Override
    public long count() {
        return character == null ? 0 : 1;
    }

    @Override
    public Optional<Character> findLastCreated() {
        return Optional.ofNullable(character);
    }
}
