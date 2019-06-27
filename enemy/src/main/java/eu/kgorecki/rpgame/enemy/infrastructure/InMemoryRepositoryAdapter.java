package eu.kgorecki.rpgame.enemy.infrastructure;

import eu.kgorecki.rpgame.enemy.domain.Enemy;
import eu.kgorecki.rpgame.enemy.domain.Id;
import eu.kgorecki.rpgame.enemy.domain.RepositoryPort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class InMemoryRepositoryAdapter implements RepositoryPort {
    private final Set<Enemy> enemies;

    public InMemoryRepositoryAdapter() {
        this(Enemy.of(0, "Troll", 3, 1, 1),
                Enemy.of(1, "Dragon", 10, 3, 3),
                Enemy.of(2, "Gnome", 4, 2, 1));
    }

    private InMemoryRepositoryAdapter(Enemy... enemies) {
        List<Enemy> enemyList = Arrays.asList(enemies);

        this.enemies = new HashSet<>(enemyList);
    }

    private InMemoryRepositoryAdapter(Set<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public Optional<Enemy> findOne(Id id) {
        return enemies
                .stream()
                .filter(enemy -> enemy.getId().equals(id))
                .findFirst();
    }

    @Override
    public void saveOrUpdate(Enemy enemy) {
        enemies.remove(enemy);
        enemies.add(enemy);
    }

    @Override
    public Optional<Enemy> findRandomEnemy() {
        if (enemies.isEmpty()) {
            return Optional.empty();
        }

        int enemyNumber = new Random().nextInt(enemies.size());

        return Optional.of((Enemy) enemies.toArray()[enemyNumber]);
    }
}
