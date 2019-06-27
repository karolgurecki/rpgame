package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.AddExperienceCommand;
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;
import eu.kgorecki.rpgame.character.dto.EquipItemCommand;
import eu.kgorecki.rpgame.character.dto.PrintCharacterStatisticsCommand;

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
                .map(character -> character.equipItem(command, userInteractionPort, itemsPort))
                .ifPresent(repositoryPort::save);
    }

    public void printStatistics(PrintCharacterStatisticsCommand command) {
        repositoryPort.findById(command.getId())
                .ifPresent(character -> character.printStatistics(userInteractionPort));
    }

    public void addExpirance(AddExperienceCommand command) {
        repositoryPort.findById(command.getId())
                .map(character -> character.addExperiencePoint(command, userInteractionPort))
                .ifPresent(repositoryPort::save);
    }
}
