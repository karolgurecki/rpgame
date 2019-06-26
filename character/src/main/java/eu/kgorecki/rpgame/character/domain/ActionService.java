package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;

public class ActionService {
    private final RepositoryPort repositoryPort;
    private final ItemsPort itemsPort;

    public ActionService(RepositoryPort repositoryPort, ItemsPort itemsPort) {
        this.repositoryPort = repositoryPort;
        this.itemsPort = itemsPort;
    }

    public void takeDamage(CharacterTakeDamageCommand command) {
        repositoryPort.findById(command.getId())
                .map(character -> character.takeDamage(command, itemsPort))
                .ifPresent(repositoryPort::save);
    }
}
