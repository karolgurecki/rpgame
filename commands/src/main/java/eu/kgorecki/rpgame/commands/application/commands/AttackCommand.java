package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.ports.CharacterPort;
import eu.kgorecki.rpgame.commands.application.ports.EnemyPort;
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;
import eu.kgorecki.rpgame.commands.application.ports.WorldPort;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;

import java.util.function.Consumer;

public class AttackCommand implements Command {

    private static final String COMMAND_STRING = "attack";

    private final EnemyPort enemyPort;
    private final CharacterPort characterPort;
    private final WorldPort worldPort;
    private final UserInteractionPort userInteractionPort;

    public AttackCommand(EnemyPort enemyPort, CharacterPort characterPort, WorldPort worldPort, UserInteractionPort userInteractionPort) {
        this.enemyPort = enemyPort;
        this.characterPort = characterPort;
        this.worldPort = worldPort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        worldPort.findEnemyInCurrentRoom()
                .ifPresentOrElse(enemyShouldTakeDamage(), () -> userInteractionPort.displayText(Messages.ENEMY_NOT_EXISTS_IN_CURRENT_ROOM));
    }

    private Consumer<EnemyId> enemyShouldTakeDamage() {
        return enemyId ->
                worldPort.findCharacterPresentInWorld()
                        .filter(characterPort::isAlive)
                        .ifPresentOrElse(characterIdShouldAttack(enemyId), () -> userInteractionPort.displayText(Messages.CHARACTER_NOT_EXISTS));
    }

    private Consumer<CharacterId> characterIdShouldAttack(EnemyId enemyId) {
        return characterId ->
                characterPort.findAttackPower(characterId)
                        .ifPresent(attackPower -> {
                            userInteractionPort.displayText(String.format(Messages.YOU_ARE_ATTACKING, attackPower));
                            enemyPort.takeDamage(attackPower, enemyId);
                            enemyPort.printStatus(enemyId);
                        });
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND_STRING;
    }
}
