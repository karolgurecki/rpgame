package eu.kgorecki.rpgame.enemy.domain;

import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPower;
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPowerQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatus;
import eu.kgorecki.rpgame.enemy.dto.EnemyStatusQuery;
import eu.kgorecki.rpgame.enemy.dto.EnemyTakeDamageCommand;
import eu.kgorecki.rpgame.enemy.dto.PringEnemyInformationCommand;

import java.util.Optional;

public class Service {

    private final RepositoryPort repositoryPort;
    private final UserInteractionPort userInteractionPort;

    public Service(RepositoryPort repositoryPort, UserInteractionPort userInteractionPort) {
        this.repositoryPort = repositoryPort;
        this.userInteractionPort = userInteractionPort;
    }

    public Optional<EnemyAttackPower> getAttackPower(EnemyAttackPowerQuery query) {
        return repositoryPort.findOne(Id.of(query.getEnemyId()))
                .map(Enemy::getAttackPower);
    }

    public void takeDamage(EnemyTakeDamageCommand command) {
        Optional<Enemy> optionalEnemy = repositoryPort.findOne(Id.of(command.getEnemyId()));

        if (optionalEnemy.isEmpty()) {
            return;
        }

        Enemy enemy = optionalEnemy.get();

        repositoryPort.saveOrUpdate(enemy.takeDamage(command));
    }

    public EnemyStatus getStatus(EnemyStatusQuery query) {
        return repositoryPort.findOne(Id.of(query.getEnemyId()))
                .map(Enemy::getStatus)
                .orElse(EnemyStatus.NOT_EXISTS);
    }

    public void pringEnemyInformation(PringEnemyInformationCommand command) {
        repositoryPort.findOne(Id.of(command.getEnemyId()))
                .ifPresent(enemy -> userInteractionPort.displayText(enemy.toString()));
    }
}
