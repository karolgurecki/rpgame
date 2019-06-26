package eu.kgorecki.rpgame.engine.domain;

public interface CommandPort {

    void execute(String command);

    String read();
}
