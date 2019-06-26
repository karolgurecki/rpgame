package eu.kgorecki.rpgame.world.domain;

import java.util.Optional;

public interface LoaderPort {

    Optional<World> load();
}
