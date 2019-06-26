package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;

public class ActionService {
    private final RepositoryPort repositoryPort;

    public ActionService(RepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    public void takeDamage(CharacterTakeDamageCommand command) {
        repositoryPort.findById(command.getId())
                .map(character -> character.takeDamage(command))
                .ifPresent(repositoryPort::save);
    }
}
