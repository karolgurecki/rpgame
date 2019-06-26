package eu.kgorecki.rpgame.world.domain;

import java.util.Optional;

public interface RepositoryPort {
    Optional<World> findWorld();

    void save(World world);
}
