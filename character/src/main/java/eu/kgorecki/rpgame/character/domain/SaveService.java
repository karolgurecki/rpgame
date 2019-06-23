package eu.kgorecki.rpgame.character.domain;

import java.util.Optional;

public class SaveService {

    private final SavePort savePort;
    private final RepositoryPort repositoryPort;
    private final UserInteractionPort displayInformationPort;

    public SaveService(SavePort savePort, RepositoryPort repositoryPort, UserInteractionPort displayInformationPort) {
        this.savePort = savePort;
        this.repositoryPort = repositoryPort;
        this.displayInformationPort = displayInformationPort;
    }

    public void save() {
        Optional<Character> character = repositoryPort.findLastCreated();

        if(character.isEmpty()){
            displayInformationPort.displayText(UserMassages.NO_CHARACTER_CANNOT_SAVE);
            return;
        }

        savePort.save(character.get());
        displayInformationPort.displayText(UserMassages.CHARACTER_SAVED);
    }
}
