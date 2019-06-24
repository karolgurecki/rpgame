package eu.kgorecki.rpgame.userinterface;

import eu.kgorecki.rpgame.userinterface.application.UserInteractionService;

public class UserInterfaceFacade {
    private final UserInteractionService service;

    UserInterfaceFacade(UserInteractionService service) {
        this.service = service;
    }

    public void displayText(String text) {
        service.displayText(text);
    }

    public String getUserInput() {
        return service.getInputFromUser();
    }

    public String getUserInputWithTextInTheSameLine(String text) {
        return service.getUserInputWithTextInTheSameLine(text);
    }
}
