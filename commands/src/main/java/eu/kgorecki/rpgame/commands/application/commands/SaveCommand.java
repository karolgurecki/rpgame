package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.SavePort;
import eu.kgorecki.rpgame.commands.application.UserInteractionPort;

public class SaveCommand implements Command {

    private static final String COMMAND = "save state";
    private final SavePort savePort;
    private final UserInteractionPort userInteractionPort;

    public SaveCommand(SavePort savePort, UserInteractionPort userInteractionPort) {
        this.savePort = savePort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        savePort.save();

        userInteractionPort.displayText(Messages.SAVE_COMPLETE);
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
