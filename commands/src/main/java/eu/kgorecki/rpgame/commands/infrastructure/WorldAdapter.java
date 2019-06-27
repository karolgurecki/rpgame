package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.commands.application.ports.WorldPort;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.world.WorldFacade;
import eu.kgorecki.rpgame.world.dto.CreateWorldCommand;

import java.util.Optional;

public class WorldAdapter implements WorldPort {

    private final WorldFacade worldFacade;

    public WorldAdapter(WorldFacade worldFacade) {
        this.worldFacade = worldFacade;
    }

    @Override
    public Optional<CharacterId> findCharacterPresentInWorld() {
        return worldFacade.findCharacter();
    }

    @Override
    public Optional<EnemyId> findEnemyInCurrentRoom() {
        return worldFacade.findEnemyInCurrentRoom();
    }

    @Override
    public Optional<ItemId> findItemInCurrentRoom() {
        return worldFacade.findItemInCurrentRoom();
    }

    @Override
    public void putCharacterInTheWorld(CharacterId characterId) {
        worldFacade.createTheWorld(new CreateWorldCommand(characterId));
    }
}
