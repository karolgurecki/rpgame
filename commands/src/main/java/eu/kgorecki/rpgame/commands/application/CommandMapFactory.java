package eu.kgorecki.rpgame.commands.application;

import eu.kgorecki.rpgame.character.CharacterFacadeFactory;
import eu.kgorecki.rpgame.commands.application.commands.AttackCommand;
import eu.kgorecki.rpgame.commands.application.commands.CreateCharacterCommand;
import eu.kgorecki.rpgame.commands.application.commands.MoveAheadCommand;
import eu.kgorecki.rpgame.commands.application.commands.MoveLeftCommand;
import eu.kgorecki.rpgame.commands.application.commands.MoveRightCommand;
import eu.kgorecki.rpgame.commands.infrastructure.CharacterAdapter;
import eu.kgorecki.rpgame.commands.infrastructure.EnemyAdapter;
import eu.kgorecki.rpgame.commands.infrastructure.UserInteractionAdapter;
import eu.kgorecki.rpgame.commands.infrastructure.WorldAdapter;
import eu.kgorecki.rpgame.enemy.EnemyFacadeFactory;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;
import eu.kgorecki.rpgame.world.WorldFacadeFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandMapFactory {

    private static final EnemyPort ENEMY_PORT = new EnemyAdapter(EnemyFacadeFactory.createFacade());
    private static final CharacterPort CHARACTER_PORT = new CharacterAdapter(CharacterFacadeFactory.createFacade(),
            WorldFacadeFactory.createFacade());
    private static final WorldPort WORLD_PORT = new WorldAdapter(WorldFacadeFactory.createFacade());
    private static final UserInteractionPort USER_INTERACTION_PORT = new UserInteractionAdapter(UserInterfaceFacadeFactory
            .createFacade());

    private static final List<Command> COMMANDS = List.of(
            new AttackCommand(ENEMY_PORT, CHARACTER_PORT, WORLD_PORT, USER_INTERACTION_PORT),
            new MoveLeftCommand(CHARACTER_PORT, WORLD_PORT, USER_INTERACTION_PORT),
            new MoveRightCommand(CHARACTER_PORT, WORLD_PORT, USER_INTERACTION_PORT),
            new MoveAheadCommand(CHARACTER_PORT, WORLD_PORT, USER_INTERACTION_PORT),
            new CreateCharacterCommand(WORLD_PORT, CHARACTER_PORT, USER_INTERACTION_PORT)
    );

    private CommandMapFactory() {
    }

    public static Map<String, Command> commandMap() {
        return commandMap(COMMANDS);
    }

    private static Map<String, Command> commandMap(List<Command> commands) {
        return commands
                .stream()
                .collect(Collectors.toMap(command -> command.getStringForWithCommandMustByExecuted().toUpperCase(),
                        Function.identity()));
    }
}
