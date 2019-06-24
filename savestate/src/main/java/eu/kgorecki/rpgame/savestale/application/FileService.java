package eu.kgorecki.rpgame.savestale.application;

import eu.kgorecki.rpgame.savestale.infrastructure.CannotLoadGameStateException;
import eu.kgorecki.rpgame.savestale.infrastructure.CannotSaveGameStateException;
import eu.kgorecki.rpgame.savestale.infrastructure.GameStateNotExistsException;

import java.util.Optional;

public class FileService {

    private final GameStateLoaderPort gameStateLoaderPort;
    private final GameStateSaverPort gameStateSaverPort;
    private final UserInteractionPort userInteractionPort;

    public FileService(GameStateLoaderPort gameStateLoaderPort, GameStateSaverPort gameStateSaverPort, UserInteractionPort userInteractionPort) {
        this.gameStateLoaderPort = gameStateLoaderPort;
        this.gameStateSaverPort = gameStateSaverPort;
        this.userInteractionPort = userInteractionPort;
    }

    public <T> void save(T objectToSave) {
        try {
            gameStateSaverPort.saveGameState(objectToSave, objectToSave.getClass().getSimpleName());
        } catch (CannotSaveGameStateException e) {
            userInteractionPort.displayText(e.getMessage());
        }
    }

    public <T> Optional<T> load(Class<T> clazzOfObjectToLoad) {
        try {
            return gameStateLoaderPort.loadGameState(clazzOfObjectToLoad.getSimpleName(), clazzOfObjectToLoad);
        } catch (CannotLoadGameStateException | GameStateNotExistsException e) {
            userInteractionPort.displayText(e.getMessage());

            return Optional.empty();
        }
    }
}
