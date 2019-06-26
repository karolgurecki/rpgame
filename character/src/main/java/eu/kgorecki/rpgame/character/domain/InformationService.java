package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterAttackPower;
import eu.kgorecki.rpgame.character.dto.CharacterAttackPowerQuery;
import eu.kgorecki.rpgame.character.dto.CharacterStatus;
import eu.kgorecki.rpgame.character.dto.CharacterStatusQuery;

import java.util.Optional;

public class InformationService {

    private final RepositoryPort repositoryPort;
    private final ItemsPort itemsPort;

    public InformationService(RepositoryPort repositoryPort, ItemsPort itemsPort) {
        this.repositoryPort = repositoryPort;
        this.itemsPort = itemsPort;
    }

    public Optional<CharacterAttackPower> findAttackPower(CharacterAttackPowerQuery query) {
        return repositoryPort.findById(query.getCharacterId())
                .map(character -> character.getCharacterAttackPower(itemsPort));
    }

    public Optional<CharacterStatus> findCharacterStatus(CharacterStatusQuery query) {
        return repositoryPort.findById(query.getCharacterId())
                .map(character -> character.getStatus());
    }
}
