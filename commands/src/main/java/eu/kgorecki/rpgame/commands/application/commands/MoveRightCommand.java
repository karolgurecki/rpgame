package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.ports.CharacterPort;
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;
import eu.kgorecki.rpgame.commands.application.ports.WorldPort;

public class MoveRightCommand implements Command {
    private static final String COMMAND = "move right";

    private final CharacterPort characterPort;
    private final WorldPort worldPort;
    private final UserInteractionPort userInteractionPort;

    public MoveRightCommand(CharacterPort characterPort, WorldPort worldPort, UserInteractionPort userInteractionPort) {
        this.characterPort = characterPort;
        this.worldPort = worldPort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        worldPort.findCharacterPresentInWorld()
                .filter(characterPort::isAlive)
                .ifPresentOrElse(characterPort::moveRight,
                        () -> userInteractionPort.displayText(Messages.CHARACTER_NOT_EXISTS));
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
