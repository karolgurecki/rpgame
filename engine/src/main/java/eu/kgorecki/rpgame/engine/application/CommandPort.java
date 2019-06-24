package eu.kgorecki.rpgame.engine.application;

public interface CommandPort {

    void execute(String command);

    String read();
}
