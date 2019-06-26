package eu.kgorecki.rpgame.character;

import eu.kgorecki.rpgame.character.domain.ActionService;
import eu.kgorecki.rpgame.character.domain.CreationService;
import eu.kgorecki.rpgame.character.domain.LoadService;
import eu.kgorecki.rpgame.character.domain.SaveService;
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus;
import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;

import java.util.Optional;

public class CharacterFacade {

    private final CreationService creationService;
    private final SaveService saveService;
    private final LoadService loadService;
    private final ActionService actionService;

    CharacterFacade(CreationService creationService, SaveService saveService, LoadService loadService,
                    ActionService actionService) {
        this.creationService = creationService;
        this.saveService = saveService;
        this.loadService = loadService;
        this.actionService = actionService;
    }

    public CharacterCreationStatus createCharacter() {
        return creationService.createCharacter();
    }

    public Optional<CharacterId> findLastCreated() {
        return creationService.findLastCreated();
    }

    public void saveCharacters() {
        saveService.save();
    }

    public void loadCharacter() {
        loadService.load();
    }

    public void takeDamage(CharacterTakeDamageCommand characterTakeDamageCommand) {
        actionService.takeDamage(characterTakeDamageCommand);
    }
}
