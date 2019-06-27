package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.character.CharacterFacade;
import eu.kgorecki.rpgame.character.dto.CharacterAttackPower;
import eu.kgorecki.rpgame.character.dto.CharacterAttackPowerQuery;
import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.character.dto.CharacterStatus;
import eu.kgorecki.rpgame.character.dto.CharacterStatusQuery;
import eu.kgorecki.rpgame.commands.application.CharacterPort;
import eu.kgorecki.rpgame.world.WorldFacade;
import eu.kgorecki.rpgame.world.dto.MoveCharacterCommand;

import java.util.Optional;

public class CharacterAdapter implements CharacterPort {

    private final CharacterFacade characterFacade;
    private final WorldFacade worldFacade;

    public CharacterAdapter(CharacterFacade characterFacade, WorldFacade worldFacade) {
        this.characterFacade = characterFacade;
        this.worldFacade = worldFacade;
    }

    @Override
    public Optional<Integer> findAttackPower(CharacterId characterId) {
        return characterFacade.findAttackPower(new CharacterAttackPowerQuery(characterId))
                .map(CharacterAttackPower::getAttackPower);
    }

    @Override
    public boolean isAlive(CharacterId characterId) {
        return characterFacade.findCharacterStatus(new CharacterStatusQuery(characterId))
                .filter(characterStatus -> characterStatus == CharacterStatus.ALIVE)
                .isPresent();
    }

    @Override
    public void moveLeft(CharacterId characterId) {
        worldFacade.moveCharacter(MoveCharacterCommand.MOVE_LEFT);
    }

    @Override
    public void moveRight(CharacterId characterId) {
        worldFacade.moveCharacter(MoveCharacterCommand.MOVE_RIGHT);
    }

    @Override
    public void moveAhead(CharacterId characterId) {
        worldFacade.moveCharacter(MoveCharacterCommand.MOVE_AHEAD);
    }

    @Override
    public Optional<CharacterId> create() {
        characterFacade.createCharacter();

        return characterFacade.findLastCreated();
    }
}
