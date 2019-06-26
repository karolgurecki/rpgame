package eu.kgorecki.rpgame.enemy.domain;

import java.util.Optional;

public interface RepositoryPort {

    Optional<Enemy> findOne(Id id);

    void saveOrUpdate(Enemy enemy);

    Optional<Enemy> findRandomEnemy();
}
