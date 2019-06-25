package eu.kgorecki.rpgame.enemy;

import eu.kgorecki.rpgame.enemy.domain.Service;
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPower;
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPowerQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatus;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatusQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyTakeDamageCommand;
import eu.kgorecki.rpgame.enemy.dto.PringEnemyInformationCommand;

import java.util.Optional;

public class EnemyFacade {

    private final Service service;

    public EnemyFacade(Service service) {
        this.service = service;
    }

    public Optional<EnemyAttackPower> getAttackPower(EnemyAttackPowerQuery query) {
        return service.getAttackPower(query);
    }

    public void takeDemage(EnemyTakeDamageCommand command) {
        service.takeDamage(command);
    }

    public EnemyStatus getStatus(EnemyStatusQuery query) {
        return service.getStatus(query);
    }

    public void pringEnemyInformation(PringEnemyInformationCommand command) {
        service.pringEnemyInformation(command);
    }
}