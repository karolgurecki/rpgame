package eu.kgorecki.rpgame.userinterface.infrastructure;

import eu.kgorecki.rpgame.userinterface.application.DisplayPort;

public class ToConsoleDisplayAdapter implements DisplayPort {
    @Override
    public void displayText(String text) {
        System.out.println(text);
    }
}
