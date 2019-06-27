package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.CharacterPort;
import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.UserInteractionPort;
import eu.kgorecki.rpgame.commands.application.WorldPort;

public class MoveAheadCommand implements Command {
    private static final String COMMAND = "move ahead";

    private final CharacterPort characterPort;
    private final WorldPort worldPort;
    private final UserInteractionPort userInteractionPort;

    public MoveAheadCommand(CharacterPort characterPort, WorldPort worldPort, UserInteractionPort userInteractionPort) {
        this.characterPort = characterPort;
        this.worldPort = worldPort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        worldPort.findCharacterPresentInWorld()
                .filter(characterPort::isAlive)
                .ifPresentOrElse(characterPort::moveAhead,
                        () -> userInteractionPort.displayText(Messages.CHARACTER_NOT_EXISTS));
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
