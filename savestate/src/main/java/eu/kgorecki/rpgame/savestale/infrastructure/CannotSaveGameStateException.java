package eu.kgorecki.rpgame.savestale.infrastructure;

public class CannotSaveGameStateException extends Exception {

    CannotSaveGameStateException() {
        super("Cannot save game progress.\n" +
                "Please check if you have permission to write data to your home folder.");
    }
}
