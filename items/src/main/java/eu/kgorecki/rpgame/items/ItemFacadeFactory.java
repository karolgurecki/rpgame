package eu.kgorecki.rpgame.items;

import eu.kgorecki.rpgame.items.domain.DomainService;
import eu.kgorecki.rpgame.items.domain.RepositoryPort;
import eu.kgorecki.rpgame.items.infrastructure.InMemoryRepositoryAdapter;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

public class ItemFacadeFactory {

    private static ItemFacade instance;

    public static ItemFacade createFacade() {
        if (instance == null) {
            instance = createFacade(UserInterfaceFacadeFactory.createFacade(), new InMemoryRepositoryAdapter());
        }

        return instance;
    }

    private static ItemFacade createFacade(UserInterfaceFacade userInterfaceFacade, RepositoryPort repositoryPort) {
        DomainService service = new DomainService(repositoryPort, userInterfaceFacade);

        return new ItemFacade(service);
    }
}
