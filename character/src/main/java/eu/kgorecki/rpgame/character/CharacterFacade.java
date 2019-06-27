package eu.kgorecki.rpgame.character;

import eu.kgorecki.rpgame.character.domain.ActionService;
import eu.kgorecki.rpgame.character.domain.CreationService;
import eu.kgorecki.rpgame.character.domain.InformationService;
import eu.kgorecki.rpgame.character.domain.LoadService;
import eu.kgorecki.rpgame.character.domain.SaveService;
import eu.kgorecki.rpgame.character.dto.CharacterAttackPower;
import eu.kgorecki.rpgame.character.dto.CharacterAttackPowerQuery;
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus;
import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.character.dto.CharacterStatus;
import eu.kgorecki.rpgame.character.dto.CharacterStatusQuery;
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;
import eu.kgorecki.rpgame.character.dto.EquipItemCommand;

import java.util.Optional;

public class CharacterFacade {

    private final CreationService creationService;
    private final SaveService saveService;
    private final LoadService loadService;
    private final ActionService actionService;
    private final InformationService informationService;

    CharacterFacade(CreationService creationService, SaveService saveService, LoadService loadService,
                    ActionService actionService, InformationService informationService) {
        this.creationService = creationService;
        this.saveService = saveService;
        this.loadService = loadService;
        this.actionService = actionService;
        this.informationService = informationService;
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

    public void equipItem(EquipItemCommand command) {
        actionService.equipItem(command);
    }

    public Optional<CharacterStatus> findCharacterStatus(CharacterStatusQuery query) {
        return informationService.findCharacterStatus(query);
    }

    public Optional<CharacterAttackPower> findAttackPower(CharacterAttackPowerQuery query) {
        return informationService.findAttackPower(query);
    }
}
