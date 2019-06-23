package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

import java.util.Optional;

public class SaveService {

    private final SavePort savePort;
    private final RepositoryPort repositoryPort;
    private final UserInterfaceFacade userInterfaceFacade;

    public SaveService(SavePort savePort, RepositoryPort repositoryPort, UserInterfaceFacade userInterfaceFacade) {
        this.savePort = savePort;
        this.repositoryPort = repositoryPort;
        this.userInterfaceFacade = userInterfaceFacade;
    }

    public void save() {
        Optional<Character> character = repositoryPort.findLastCreated();

        if(character.isEmpty()){
            userInterfaceFacade.displayText(UserMassages.NO_CHARACTER_CANNOT_SAVE);
            return;
        }

        savePort.save(character.get());
        userInterfaceFacade.displayText(UserMassages.CHARACTER_SAVED);
    }
}
