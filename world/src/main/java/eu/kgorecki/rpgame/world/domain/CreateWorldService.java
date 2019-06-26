package eu.kgorecki.rpgame.world.domain;

import eu.kgorecki.rpgame.world.dto.CreateWorldCommand;

public class CreateWorldService {
    private final RepositoryPort repositoryPort;
    private final WorldRoomCreatorPort worldRoomCreatorPort;

    public CreateWorldService(RepositoryPort repositoryPort, WorldRoomCreatorPort worldRoomCreatorPort) {
        this.repositoryPort = repositoryPort;
        this.worldRoomCreatorPort = worldRoomCreatorPort;
    }

    public void create(CreateWorldCommand command) {
        Room startingRoom = worldRoomCreatorPort.createRooms();

        World world = new World(command.getCharacterId(), startingRoom);

        repositoryPort.save(world);
    }
}
