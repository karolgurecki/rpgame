package eu.kgorecki.rpgame.engine.infrastructure;

import eu.kgorecki.rpgame.character.CharacterFacade;
import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;
import eu.kgorecki.rpgame.enemy.EnemyFacade;
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPower;
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPowerQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.engine.domain.EnemyActionPort;
import eu.kgorecki.rpgame.world.WorldFacade;

import java.util.Optional;
import java.util.function.Consumer;

public class EnemyActionAdapter implements EnemyActionPort {
    private final EnemyFacade enemyFacade;
    private final CharacterFacade characterFacade;
    private final WorldFacade worldFacade;

    public EnemyActionAdapter(EnemyFacade enemyFacade, CharacterFacade characterFacade, WorldFacade worldFacade) {
        this.enemyFacade = enemyFacade;
        this.characterFacade = characterFacade;
        this.worldFacade = worldFacade;
    }

    @Override
    public void executeEnemyTurn() {
        worldFacade.findEnemyInCurrentRoom().ifPresent(commandEnemyToAttack());
    }

    private Consumer<EnemyId> commandEnemyToAttack() {
        return enemyId -> {
            EnemyAttackPowerQuery enemyAttackPowerQuery = new EnemyAttackPowerQuery(enemyId);

            enemyFacade.findAttackPower(enemyAttackPowerQuery).ifPresent(commandCharacterToTakeDamage());
        };
    }

    Consumer<EnemyAttackPower> commandCharacterToTakeDamage() {
        return enemyAttackPower -> {
            Optional<CharacterId> characterIdOptional = worldFacade.findCharacter();

            if (characterIdOptional.isEmpty()) {
                return;
            }

            CharacterTakeDamageCommand characterTakeDamageCommand =
                    new CharacterTakeDamageCommand(characterIdOptional.get(), enemyAttackPower.getAttackPower());

            characterFacade.takeDamage(characterTakeDamageCommand);
        };
    }
}
