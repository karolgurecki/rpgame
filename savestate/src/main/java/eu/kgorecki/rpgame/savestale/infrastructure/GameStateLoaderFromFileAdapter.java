package eu.kgorecki.rpgame.savestale.infrastructure;

import eu.kgorecki.rpgame.savestale.application.GameStateLoaderPort;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class GameStateLoaderFromFileAdapter implements GameStateLoaderPort {

    private final SavePathResolver savePathResolver;

    public GameStateLoaderFromFileAdapter() {
        this(new SavePathResolver());
    }

    GameStateLoaderFromFileAdapter(SavePathResolver savePathResolver) {
        this.savePathResolver = savePathResolver;
    }

    @Override
    public <T> Optional<T> loadGameState(String saveName, Class<T> classOfObjectToDeserialization) throws GameStateNotExistsException, CannotLoadGameStateException {
        String filePath = savePathResolver.getSaveStateFilePath(saveName);

        checkIfSaveStateFileExists(filePath);

        return deserializeSaveFile(filePath);
    }

    private <T> Optional<T> deserializeSaveFile(String filePath) throws CannotLoadGameStateException {
        try (InputStream inputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

            return Optional.ofNullable((T) objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new CannotLoadGameStateException();
        }
    }

    private void checkIfSaveStateFileExists(String filePath) throws GameStateNotExistsException {
        if (!Files.exists(Path.of(filePath))) {
            throw new GameStateNotExistsException();
        }
    }
}
