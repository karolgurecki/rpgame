package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.commands.application.ports.EnemyPort;
import eu.kgorecki.rpgame.enemy.EnemyFacade;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatus;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatusQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyTakeDamageCommand;
import eu.kgorecki.rpgame.enemy.dto.PrintEnemyInformationCommand;

public class EnemyAdapter implements EnemyPort {

    private final EnemyFacade enemyFacade;

    public EnemyAdapter(EnemyFacade enemyFacade) {
        this.enemyFacade = enemyFacade;
    }

    @Override
    public void takeDamage(Integer attackPower, EnemyId enemyId) {
        enemyFacade.takeDamage(new EnemyTakeDamageCommand(enemyId, attackPower));
    }

    @Override
    public void printStatus(EnemyId enemyId) {
        enemyFacade.printEnemyInformation(new PrintEnemyInformationCommand(enemyId));
    }

    @Override
    public boolean isAlive(EnemyId enemyId) {
        return enemyFacade.getStatus(new EnemyStatusQuery(enemyId)) == EnemyStatus.ALIVE;
    }

}
