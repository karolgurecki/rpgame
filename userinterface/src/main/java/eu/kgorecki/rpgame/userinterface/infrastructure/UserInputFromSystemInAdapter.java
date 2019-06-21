package eu.kgorecki.rpgame.userinterface.infrastructure;

import eu.kgorecki.rpgame.userinterface.application.UserInputPort;

import java.util.Scanner;

public class UserInputFromSystemInAdapter implements UserInputPort {
    @Override
    public String getInputFromUser() {
        final Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
