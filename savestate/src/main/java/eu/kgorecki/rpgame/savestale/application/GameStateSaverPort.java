package eu.kgorecki.rpgame.savestale.application;

import eu.kgorecki.rpgame.savestale.infrastructure.CannotSaveGameStateException;

public interface GameStateSaverPort {

    <T> void saveGameState(T objectToSave, String name) throws CannotSaveGameStateException;
}
