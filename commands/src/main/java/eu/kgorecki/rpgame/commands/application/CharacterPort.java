package eu.kgorecki.rpgame.commands.application;

import eu.kgorecki.rpgame.character.dto.CharacterId;

import java.util.Optional;

public interface CharacterPort {
    Optional<Integer> findAttackPower(CharacterId characterId);

    boolean isAlive(CharacterId characterId);

    void moveLeft(CharacterId characterId);

    void moveRight(CharacterId characterId);

    void moveAhead(CharacterId characterId);

    Optional<CharacterId> create();
}
