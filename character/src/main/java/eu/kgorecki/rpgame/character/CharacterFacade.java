package eu.kgorecki.rpgame.character;

import eu.kgorecki.rpgame.character.domain.CreationService;
import eu.kgorecki.rpgame.character.domain.LoadService;
import eu.kgorecki.rpgame.character.domain.SaveService;
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus;
import eu.kgorecki.rpgame.character.dto.CharacterId;

import java.util.Optional;

public class CharacterFacade {

    private final CreationService creationService;
    private final SaveService saveService;
    private final LoadService loadService;

    CharacterFacade(CreationService creationService, SaveService saveService, LoadService loadService) {
        this.creationService = creationService;
        this.saveService = saveService;
        this.loadService = loadService;
    }

    public CharacterCreationStatus createCharacter(){
        return creationService.createCharacter();
    }

    public Optional<CharacterId> findLastCreated(){
        return creationService.findLastCreated();
    }

    public void saveCharacters(){
        saveService.save();
    }
}
