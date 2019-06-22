package eu.kgorecki.rpgame.savestale.infrastructure;

public class GameStateNotExistsException extends Exception {

    GameStateNotExistsException() {
        super("Cannot load save state because save file is not present");
    }
}
