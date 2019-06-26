package eu.kgorecki.rpgame.commands.application;

public interface Command {
    void execute();
    String getStringForWithCommandMustByExecuted();
}
