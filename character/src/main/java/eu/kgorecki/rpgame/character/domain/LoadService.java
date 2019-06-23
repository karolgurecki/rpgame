package eu.kgorecki.rpgame.character.domain;

import java.util.Optional;

public class LoadService {
    private final LoadPort loadPort;
    private final RepositoryPort repositoryPort;
    private final UserInteractionPort displayIntermationPort;

    public LoadService(LoadPort loadPort, RepositoryPort repositoryPort, UserInteractionPort displayIntermationPort) {
        this.loadPort = loadPort;
        this.repositoryPort = repositoryPort;
        this.displayIntermationPort = displayIntermationPort;
    }

    public void load() {
        Optional<Character> character = loadPort.load();

        if(character.isEmpty()){
            displayIntermationPort.displayText(UserMassages.CHARACTER_NOT_LOADED);
            return;
        }

        repositoryPort.save(character.get());
        displayIntermationPort.displayText(UserMassages.CHARACTER_LOADED);
    }
}
