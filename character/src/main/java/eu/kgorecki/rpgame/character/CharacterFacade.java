package eu.kgorecki.rpgame.character;

import eu.kgorecki.rpgame.character.domain.CreationService;
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus;
import eu.kgorecki.rpgame.character.dto.CharacterId;

import java.util.Optional;

public class CharacterFacade {

    private final CreationService creationService;

    CharacterFacade(CreationService creationService) {
        this.creationService = creationService;
    }

    public CharacterCreationStatus createCharacter(){
        return creationService.createCharacter();
    }

    public Optional<CharacterId> findLastCreated(){
        return creationService.findLastCreated();
    }
}
