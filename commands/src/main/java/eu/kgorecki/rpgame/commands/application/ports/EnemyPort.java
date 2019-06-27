package eu.kgorecki.rpgame.commands.application.ports;

import eu.kgorecki.rpgame.enemy.dto.EnemyId;

public interface EnemyPort {
    void takeDamage(Integer attackPower, EnemyId enemyId);

    void printStatus(EnemyId enemyId);
}
