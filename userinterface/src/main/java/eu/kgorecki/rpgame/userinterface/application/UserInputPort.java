package eu.kgorecki.rpgame.userinterface.application;

public interface UserInputPort {
    String getInputFromUser();

    String getUserInputWithTextInTheSameLine(String text);
}
