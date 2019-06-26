package eu.kgorecki.rpgame.world.domain;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.world.dto.MoveCharacterCommand;

import java.util.Optional;

public class ManageWorldStateService {

    private final RepositoryPort repositoryPort;
    private final SaverPort saverPort;
    private final LoaderPort loaderPort;
    private final UserInteractionPort userInteractionPort;

    public ManageWorldStateService(RepositoryPort repositoryPort, SaverPort saverPort, LoaderPort loaderPort,
                                   UserInteractionPort userInteractionPort) {
        this.repositoryPort = repositoryPort;
        this.saverPort = saverPort;
        this.loaderPort = loaderPort;
        this.userInteractionPort = userInteractionPort;
    }

    public Optional<CharacterId> getCharacter() {
        return repositoryPort
                .findWorld()
                .map(World::getCharacter);
    }

    public Optional<EnemyId> findEnemyInCurrentRoom() {
        return repositoryPort
                .findWorld()
                .map(World::getEnemyFromCurrentRoom);
    }

    public void moveCharacter(MoveCharacterCommand command) {
        repositoryPort.findWorld()
                .map(world -> world.moveCharacter(command, userInteractionPort))
                .ifPresent(repositoryPort::save);
    }

    public Optional<ItemId> findItemInCurrentRoom() {
        return repositoryPort
                .findWorld()
                .map(World::getItemFromCurrentRoom);
    }

    public void saveState() {
        repositoryPort
                .findWorld()
                .ifPresent(saverPort::save);
    }

    public void loadState() {
        loaderPort
                .load()
                .ifPresent(repositoryPort::save);
    }
}
