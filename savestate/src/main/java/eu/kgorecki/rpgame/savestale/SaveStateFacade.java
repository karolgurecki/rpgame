package eu.kgorecki.rpgame.savestale;

import eu.kgorecki.rpgame.savestale.application.FileService;

import java.util.Optional;

public class SaveStateFacade {

    private final FileService service;

    SaveStateFacade(FileService service) {
        this.service = service;
    }

    public <T> void save(T objectToSave) {
        service.save(objectToSave);
    }

    public <T> Optional<T> load(Class<T> clazzOfObjectToLoad) {
        return service.load(clazzOfObjectToLoad);
    }
}
