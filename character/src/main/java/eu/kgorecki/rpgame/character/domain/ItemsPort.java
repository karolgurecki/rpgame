package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.items.dto.ItemId;

import java.util.Optional;

public interface ItemsPort {
    Optional<Integer> findAttackPower(ItemId itemId);

    Optional<Integer> findDefencePower(ItemId itemId);
}
