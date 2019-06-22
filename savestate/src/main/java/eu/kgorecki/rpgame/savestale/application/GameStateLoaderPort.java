package eu.kgorecki.rpgame.savestale.application;

import eu.kgorecki.rpgame.savestale.infrastructure.CannotLoadGameStateException;
import eu.kgorecki.rpgame.savestale.infrastructure.GameStateNotExistsException;

import java.util.Optional;

public interface GameStateLoaderPort {

    <T> Optional<T> loadGameState(String saveName, Class<T> classOfObjectToDeserialization) throws GameStateNotExistsException, CannotLoadGameStateException;
}
