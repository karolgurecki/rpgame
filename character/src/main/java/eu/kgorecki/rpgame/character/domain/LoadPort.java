package eu.kgorecki.rpgame.character.domain;

import java.util.Optional;

public interface LoadPort {

    Optional<Character> load();
}
