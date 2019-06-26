package eu.kgorecki.rpgame.world.infrastructure;

import eu.kgorecki.rpgame.world.domain.RepositoryPort;
import eu.kgorecki.rpgame.world.domain.World;

import java.util.Optional;

public class InMemoryRepositoryAdapter implements RepositoryPort {
    private World world;

    @Override
    public Optional<World> findWorld() {
        return Optional.ofNullable(world);
    }

    @Override
    public void save(World world) {
        this.world = world;
    }
}
