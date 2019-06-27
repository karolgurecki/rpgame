package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.character.CharacterFacade;
import eu.kgorecki.rpgame.character.dto.CharacterAttackPower;
import eu.kgorecki.rpgame.character.dto.CharacterAttackPowerQuery;
import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.character.dto.CharacterStatus;
import eu.kgorecki.rpgame.character.dto.CharacterStatusQuery;
import eu.kgorecki.rpgame.commands.application.CharacterPort;

import java.util.Optional;

public class CharacterAdapter implements CharacterPort {

    private final CharacterFacade characterFacade;

    public CharacterAdapter(CharacterFacade characterFacade) {
        this.characterFacade = characterFacade;
    }

    @Override
    public Optional<Integer> findAttackPower(CharacterId characterId) {
        return characterFacade.findAttackPower(new CharacterAttackPowerQuery(characterId))
                .map(CharacterAttackPower::getAttackPower);
    }

    @Override
    public void loadState() {
        characterFacade.loadCharacter();
    }

    @Override
    public void saveState() {
        characterFacade.saveCharacters();
    }

    @Override
    public boolean isAlive(CharacterId characterId) {
        return characterFacade.findCharacterStatus(new CharacterStatusQuery(characterId))
                .filter(characterStatus -> characterStatus == CharacterStatus.ALIVE)
                .isPresent();
    }
}
