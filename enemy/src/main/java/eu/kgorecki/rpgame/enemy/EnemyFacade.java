package eu.kgorecki.rpgame.enemy;

import eu.kgorecki.rpgame.enemy.domain.Service;
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPower;
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPowerQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatus;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatusQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyTakeDamageCommand;
import eu.kgorecki.rpgame.enemy.dto.PrintEnemyInformationCommand;

import java.util.Optional;

public class EnemyFacade {

    private final Service service;

    EnemyFacade(Service service) {
        this.service = service;
    }

    public Optional<EnemyAttackPower> findAttackPower(EnemyAttackPowerQuery query) {
        return service.getAttackPower(query);
    }

    public void takeDamage(EnemyTakeDamageCommand command) {
        service.takeDamage(command);
    }

    public EnemyStatus getStatus(EnemyStatusQuery query) {
        return service.getStatus(query);
    }

    public void printEnemyInformation(PrintEnemyInformationCommand command) {
        service.printEnemyInformation(command);
    }

    public Optional<EnemyId> findRandomEnemy() {
        return service.findRandomEnemy();
    }
}