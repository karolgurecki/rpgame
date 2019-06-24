package eu.kgorecki.rpgame.engine;

import eu.kgorecki.rpgame.engine.application.CommandPort;
import eu.kgorecki.rpgame.engine.application.EnemyActionPort;
import eu.kgorecki.rpgame.engine.application.Service;
import eu.kgorecki.rpgame.engine.infrastructure.CommandPortAdapter;
import eu.kgorecki.rpgame.engine.infrastructure.EnemyActionAdapter;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

public class GameEngineFacadeFactory {

    private static GameEngineFacade instance;

    public static GameEngineFacade createFacade() {
        if (instance == null) {
            instance = createFacade(new CommandPortAdapter(UserInterfaceFacadeFactory.createFacade()),
                    new EnemyActionAdapter());
        }

        return instance;
    }

    private static GameEngineFacade createFacade(CommandPort commandPort, EnemyActionPort enemyActionPort) {
        return new GameEngineFacade(new Service(commandPort, enemyActionPort));
    }
}
