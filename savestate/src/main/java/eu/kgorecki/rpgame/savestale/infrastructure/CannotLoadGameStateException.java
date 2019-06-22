package eu.kgorecki.rpgame.savestale.infrastructure;

public class CannotLoadGameStateException extends Exception {
    CannotLoadGameStateException() {
        super("Cannot load game progress.\n" +
                "Please check if you have permission to read data from your home folder.");
    }
}
