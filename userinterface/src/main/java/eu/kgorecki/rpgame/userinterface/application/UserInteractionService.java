package eu.kgorecki.rpgame.userinterface.application;

public class UserInteractionService {

    private final DisplayPort displayInfrastructureService;
    private final UserInputPort userInputPort;

    public UserInteractionService(DisplayPort displayInfrastructureService, UserInputPort userInputPort) {
        this.displayInfrastructureService = displayInfrastructureService;
        this.userInputPort = userInputPort;
    }

    public void displayText(String text) {
        displayInfrastructureService.displayText(text);
    }

    public String getInputFromUser() {
        return userInputPort.getInputFromUser();
    }

    public String getUserInputWithTextInTheSameLine(String text) {
        return userInputPort.getUserInputWithTextInTheSameLine(text);
    }
}
