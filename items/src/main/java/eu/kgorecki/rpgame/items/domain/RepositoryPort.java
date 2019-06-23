package eu.kgorecki.rpgame.items.domain;

import java.util.Optional;

public interface RepositoryPort {

    Optional<Item> findOne(Id of);

    int count();
}
