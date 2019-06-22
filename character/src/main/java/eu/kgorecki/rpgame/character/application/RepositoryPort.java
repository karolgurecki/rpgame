package eu.kgorecki.rpgame.character.application;

import eu.kgorecki.rpgame.character.domain.Character;
import eu.kgorecki.rpgame.character.dto.CharacterId;

import java.util.Optional;

public interface RepositoryPort {

    void save(Character character);

    Optional<Character> findById(CharacterId id);

    long count();

    Optional<Character> findLastCreated();
}
