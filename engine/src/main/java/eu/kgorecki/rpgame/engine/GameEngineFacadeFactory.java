package eu.kgorecki.rpgame.engine;

import eu.kgorecki.rpgame.character.CharacterFacadeFactory;
import eu.kgorecki.rpgame.commands.CommandFacadeFactory;
import eu.kgorecki.rpgame.enemy.EnemyFacadeFactory;
import eu.kgorecki.rpgame.engine.domain.CommandPort;
import eu.kgorecki.rpgame.engine.domain.EnemyActionPort;
import eu.kgorecki.rpgame.engine.domain.Service;
import eu.kgorecki.rpgame.engine.infrastructure.CommandPortAdapter;
import eu.kgorecki.rpgame.engine.infrastructure.EnemyActionAdapter;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;
import eu.kgorecki.rpgame.world.WorldFacadeFactory;

public class GameEngineFacadeFactory {

    private static GameEngineFacade instance;

    public static GameEngineFacade createFacade() {
        if (instance == null) {
            CommandPortAdapter commandPort = new CommandPortAdapter(UserInterfaceFacadeFactory.createFacade(),
                    CommandFacadeFactory.createFacade());
            EnemyActionAdapter enemyActionPort = new EnemyActionAdapter(EnemyFacadeFactory.createFacade(), CharacterFacadeFactory
                    .createFacade(), WorldFacadeFactory.createFacade());

            instance = createFacade(commandPort, enemyActionPort);
        }

        return instance;
    }

    private static GameEngineFacade createFacade(CommandPort commandPort, EnemyActionPort enemyActionPort) {
        return new GameEngineFacade(new Service(commandPort, enemyActionPort));
    }
}
