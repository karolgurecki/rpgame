package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus;
import eu.kgorecki.rpgame.character.dto.CharacterId;

import java.util.Optional;

public class CreationService {

    private final RepositoryPort repositoryPort;
    private final UserInteractionPort displayInformationPort;

    public CreationService(RepositoryPort repositoryPort, UserInteractionPort displayInformationPort) {
        this.repositoryPort = repositoryPort;
        this.displayInformationPort = displayInformationPort;
    }

    public CharacterCreationStatus createCharacter() {
        if (repositoryPort.count() > 0) {
            return CharacterCreationStatus.ALREADY_CREATED;
        }

        Character character = Character.createCharacter(displayInformationPort);
        repositoryPort.save(character);

        return CharacterCreationStatus.CREATED;
    }

    public Optional<CharacterId> findLastCreated() {
        return repositoryPort.findLastCreated()
                .map(Character::getIdAsCharacterId);
    }
}
