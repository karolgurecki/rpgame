package eu.kgorecki.rpgame.world;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.world.domain.CreateWorldService;
import eu.kgorecki.rpgame.world.domain.ManageWorldStateService;
import eu.kgorecki.rpgame.world.dto.CreateWorldCommand;
import eu.kgorecki.rpgame.world.dto.MoveCharacterCommand;

import java.util.Optional;

public class WorldFacade {

    private final ManageWorldStateService manageWorldStateService;
    private final CreateWorldService createWorldService;

    WorldFacade(ManageWorldStateService manageWorldStateService, CreateWorldService createWorldService) {
        this.manageWorldStateService = manageWorldStateService;
        this.createWorldService = createWorldService;
    }

    public Optional<CharacterId> findCharacter() {
        return manageWorldStateService.getCharacter();
    }

    public Optional<EnemyId> findEnemyInCurrentRoom() {
        return manageWorldStateService.findEnemyInCurrentRoom();
    }

    public void moveCharacter(MoveCharacterCommand command) {
        manageWorldStateService.moveCharacter(command);
    }

    public Optional<ItemId> findItemInCurrentRoom() {
        return manageWorldStateService.findItemInCurrentRoom();
    }

    public void saveState() {
        manageWorldStateService.saveState();
    }

    public void loadState() {
        manageWorldStateService.loadState();
    }

    public void createTheWorld(CreateWorldCommand command) {
        createWorldService.create(command);
    }
}
