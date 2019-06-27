package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.CharacterPort;
import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.UserInteractionPort;
import eu.kgorecki.rpgame.commands.application.WorldPort;

public class MoveLeftCommand implements Command {
    private static final String COMMAND = "move left";

    private final CharacterPort characterPort;
    private final WorldPort worldPort;
    private final UserInteractionPort userInteractionPort;

    public MoveLeftCommand(CharacterPort characterPort, WorldPort worldPort, UserInteractionPort userInteractionPort) {
        this.characterPort = characterPort;
        this.worldPort = worldPort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        worldPort.findCharacterPresentInWorld()
                .filter(characterPort::isAlive)
                .ifPresentOrElse(characterPort::moveLeft,
                        () -> userInteractionPort.displayText(Messages.CHARACTER_NOT_EXISTS));
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
