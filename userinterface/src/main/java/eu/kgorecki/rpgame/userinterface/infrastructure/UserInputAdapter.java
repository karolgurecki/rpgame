package eu.kgorecki.rpgame.userinterface.infrastructure;

import eu.kgorecki.rpgame.userinterface.application.UserInputPort;

import java.io.InputStream;
import java.util.Scanner;

public class UserInputAdapter implements UserInputPort {

    private final Scanner scanner;

    public UserInputAdapter(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    @Override
    public String getInputFromUser() {
        return scanner.nextLine();
    }
}
