package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.ports.CharacterPort;
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;
import eu.kgorecki.rpgame.commands.application.ports.WorldPort;

public class CreateCharacterCommand implements Command {
    private static final String COMMAND = "create character";

    private final WorldPort worldPort;
    private final CharacterPort characterPort;
    private final UserInteractionPort userInteractionPort;

    public CreateCharacterCommand(WorldPort worldPort, CharacterPort characterPort, UserInteractionPort userInteractionPort) {
        this.worldPort = worldPort;
        this.characterPort = characterPort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        worldPort.findCharacterPresentInWorld()
                .ifPresentOrElse(characterId -> userInteractionPort.displayText(Messages.CHARACTER_ALREADY_EXISTS),
                        createCharacter());
    }

    private Runnable createCharacter() {
        return () -> characterPort.create()
                .ifPresentOrElse(worldPort::putCharacterInTheWorld,
                        () -> userInteractionPort.displayText(Messages.CHARACTER_DO_NOT_CREATED));
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
