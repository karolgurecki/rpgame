package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus;
import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

import java.util.Optional;

public class CreationService {

    private final RepositoryPort repositoryPort;
    private final UserInterfaceFacade userInterfaceFacade;

    public CreationService(RepositoryPort repositoryPort, UserInterfaceFacade userInterfaceFacade) {
        this.repositoryPort = repositoryPort;
        this.userInterfaceFacade = userInterfaceFacade;
    }

    public CharacterCreationStatus createCharacter() {
        if (repositoryPort.count() > 0) {
            return CharacterCreationStatus.ALREADY_CREATED;
        }

        Character character = Character.createCharacter(userInterfaceFacade);
        repositoryPort.save(character);

        return CharacterCreationStatus.CREATED;
    }

    public Optional<CharacterId> findLastCreated(){
        return repositoryPort.findLastCreated()
                .map(Character::getIdAsCharacterId);
    }
}
