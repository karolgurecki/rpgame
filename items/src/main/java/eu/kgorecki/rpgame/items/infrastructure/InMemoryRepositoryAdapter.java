package eu.kgorecki.rpgame.items.infrastructure;

import eu.kgorecki.rpgame.items.domain.Id;
import eu.kgorecki.rpgame.items.domain.Item;
import eu.kgorecki.rpgame.items.domain.RepositoryPort;

import java.util.List;
import java.util.Optional;

public class InMemoryRepositoryAdapter implements RepositoryPort {

    private final List<Item> items;

    public InMemoryRepositoryAdapter() {
        this(List.of(Item.shieldOf(0, "wooden shield", 1),
                Item.weaponOf(1, "wooden sword", 1),
                Item.weaponOf(2, "Steel sword", 2),
                Item.shieldOf(3, "Steel shield", 2)));
    }

    InMemoryRepositoryAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public Optional<Item> findOne(Id id) {
        return items.size() < id.getId() ? Optional.empty() : Optional.ofNullable(items.get(id.getId()));

    }

    @Override
    public int count() {
        return items.size();
    }
}
