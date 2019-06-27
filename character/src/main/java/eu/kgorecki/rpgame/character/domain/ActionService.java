package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;
import eu.kgorecki.rpgame.character.dto.EquipItemCommand;

public class ActionService {
    private final RepositoryPort repositoryPort;
    private final ItemsPort itemsPort;
    private final UserInteractionPort userInteractionPort;

    public ActionService(RepositoryPort repositoryPort, ItemsPort itemsPort, UserInteractionPort userInteractionPort) {
        this.repositoryPort = repositoryPort;
        this.itemsPort = itemsPort;
        this.userInteractionPort = userInteractionPort;
    }

    public void takeDamage(CharacterTakeDamageCommand command) {
        repositoryPort.findById(command.getId())
                .map(character -> character.takeDamage(command, itemsPort))
                .ifPresent(repositoryPort::save);
    }

    public void equipItem(EquipItemCommand command) {
        repositoryPort.findById(command.getCharacterId())
                .map(character -> character.equipItem(command, userInteractionPort))
                .ifPresent(repositoryPort::save);
    }
}
