package eu.kgorecki.rpgame.userinterface.infrastructure;

import eu.kgorecki.rpgame.userinterface.application.UserInputPort;

import java.util.Scanner;

public class UserInputFromSystemInAdapter implements UserInputPort {
    @Override
    public String getInputFromUser() {
        return getUserInputWithTextInTheSameLine("> ");
    }

    @Override
    public String getUserInputWithTextInTheSameLine(String text) {
        System.out.print(text);

        return getInput();
    }

    private String getInput() {
        final Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
