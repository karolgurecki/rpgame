package eu.kgorecki.rpgame.enemy;

import eu.kgorecki.rpgame.enemy.domain.RepositoryPort;
import eu.kgorecki.rpgame.enemy.domain.Service;
import eu.kgorecki.rpgame.enemy.domain.UserInteractionPort;
import eu.kgorecki.rpgame.enemy.infrastructure.InMemoryRepositoryAdapter;
import eu.kgorecki.rpgame.enemy.infrastructure.UserInteractionAdapter;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

public class EnemyFacadeFactory {
    private static EnemyFacade instance;

    private EnemyFacadeFactory() {

    }

    public static EnemyFacade createFacade() {
        if (instance == null) {
            instance = createFacade(new InMemoryRepositoryAdapter(), new UserInteractionAdapter(UserInterfaceFacadeFactory
                    .createFacade()));
        }

        return instance;
    }

    private static EnemyFacade createFacade(RepositoryPort repositoryPort, UserInteractionPort userInteractionPort) {
        Service service = new Service(repositoryPort, userInteractionPort);

        return new EnemyFacade(service);
    }
}
