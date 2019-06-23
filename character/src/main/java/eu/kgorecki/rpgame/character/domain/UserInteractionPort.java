package eu.kgorecki.rpgame.character.domain;

public interface UserInteractionPort {
    void displayText(String text);

    String getUserInputWithTextInTheSameLine(String text);
}
