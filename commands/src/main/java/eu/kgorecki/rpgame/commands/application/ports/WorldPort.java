package eu.kgorecki.rpgame.commands.application.ports;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.items.dto.ItemId;

import java.util.Optional;

public interface WorldPort {
    Optional<CharacterId> findCharacterPresentInWorld();

    Optional<EnemyId> findEnemyInCurrentRoom();

    Optional<ItemId> findItemInCurrentRoom();

    void putCharacterInTheWorld(CharacterId characterId);
}
