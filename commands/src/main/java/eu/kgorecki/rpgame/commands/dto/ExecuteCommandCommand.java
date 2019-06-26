package eu.kgorecki.rpgame.commands.dto;

import java.util.Objects;

public class ExecuteCommandCommand {
    private final String command;

    public ExecuteCommandCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecuteCommandCommand that = (ExecuteCommandCommand) o;
        return Objects.equals(command, that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command);
    }
}
