package eu.kgorecki.rpgame.savestale.infrastructure;

import eu.kgorecki.rpgame.savestale.application.GameStateSaverPort;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class GameStateSaverToFileAdapter implements GameStateSaverPort {

    private final SavePathResolver savePathResolver;

    public GameStateSaverToFileAdapter() {
        this(new SavePathResolver());
    }

    GameStateSaverToFileAdapter(SavePathResolver savePathResolver) {
        this.savePathResolver = savePathResolver;
    }

    @Override
    public <T> void saveGameState(T objectToSave, String name) throws CannotSaveGameStateException {
        String filePath = savePathResolver.getSaveStateFilePath(name);

        createSaveStateFile(filePath);

        serializeSaveFile(objectToSave, filePath);
    }

    private <T> void serializeSaveFile(T objectToSave, String filePath) throws CannotSaveGameStateException {
        try (OutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(objectToSave);
        } catch (IOException e) {
            throw new CannotSaveGameStateException();
        }
    }

    private void createSaveStateFile(String filePath) throws CannotSaveGameStateException {
        File saveFile = new File(filePath);

        createParentFolder(saveFile.getParentFile());
        createSaveFile(saveFile);
    }

    private void createSaveFile(File saveFile) throws CannotSaveGameStateException {
        if (saveFile.exists()) {
            saveFile.delete();
        }

        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new CannotSaveGameStateException();
        }
    }

    private void createParentFolder(File parentFolder) {
        if (!parentFolder.exists()) {
            parentFolder.mkdirs();
        }
    }
}
