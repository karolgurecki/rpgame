package eu.kgorecki.rpgame.world;

import eu.kgorecki.rpgame.savestale.SaveStateFacadeFactory;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;
import eu.kgorecki.rpgame.world.domain.CreateWorldService;
import eu.kgorecki.rpgame.world.domain.LoaderPort;
import eu.kgorecki.rpgame.world.domain.ManageWorldStateService;
import eu.kgorecki.rpgame.world.domain.RepositoryPort;
import eu.kgorecki.rpgame.world.domain.SaverPort;
import eu.kgorecki.rpgame.world.domain.UserInteractionPort;
import eu.kgorecki.rpgame.world.domain.WorldRoomCreatorPort;
import eu.kgorecki.rpgame.world.infrastructure.InMemoryRepositoryAdapter;
import eu.kgorecki.rpgame.world.infrastructure.LoaderAdapter;
import eu.kgorecki.rpgame.world.infrastructure.SaverAdapter;
import eu.kgorecki.rpgame.world.infrastructure.UserInteractionAdapter;
import eu.kgorecki.rpgame.world.infrastructure.WorldRoomCreatorAdapter;

public class WorldFacadeFactory {
    private static WorldFacade intrance;

    private WorldFacadeFactory() {
    }

    public static WorldFacade createFacade() {
        if (intrance == null) {
            RepositoryPort repositoryPort = new InMemoryRepositoryAdapter();
            SaverPort saverPort = new SaverAdapter(SaveStateFacadeFactory.createFacade());
            LoaderPort loaderPort = new LoaderAdapter(SaveStateFacadeFactory.createFacade());
            UserInteractionPort userInteractionPort = new UserInteractionAdapter(UserInterfaceFacadeFactory.createFacade());
            WorldRoomCreatorPort worldRoomCreatorPort = new WorldRoomCreatorAdapter();

            intrance = createFacade(repositoryPort, saverPort, loaderPort, userInteractionPort, worldRoomCreatorPort);
        }

        return intrance;
    }

    private static WorldFacade createFacade(RepositoryPort repositoryPort, SaverPort saverPort, LoaderPort loaderPort,
                                            UserInteractionPort userInteractionPort, WorldRoomCreatorPort worldRoomCreatorPort) {
        ManageWorldStateService service = new ManageWorldStateService(repositoryPort, saverPort, loaderPort, userInteractionPort);
        CreateWorldService createWorldService = new CreateWorldService(repositoryPort, worldRoomCreatorPort);

        return new WorldFacade(service, createWorldService);
    }
}
