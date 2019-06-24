package eu.kgorecki.rpgame.character.domain;

import java.util.Optional;

public class LoadService {
    private final LoadPort loadPort;
    private final RepositoryPort repositoryPort;
    private final UserInteractionPort userInteractionPort;

    public LoadService(LoadPort loadPort, RepositoryPort repositoryPort, UserInteractionPort userInteractionPort) {
        this.loadPort = loadPort;
        this.repositoryPort = repositoryPort;
        this.userInteractionPort = userInteractionPort;
    }

    public void load() {
        Optional<Character> character = loadPort.load();

        if (character.isEmpty()) {
            userInteractionPort.displayText(UserMassages.CHARACTER_NOT_LOADED);
            return;
        }

        repositoryPort.save(character.get());
        userInteractionPort.displayText(UserMassages.CHARACTER_LOADED);
    }
}
