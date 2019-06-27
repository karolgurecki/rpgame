package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.commands.application.ports.ExitPort;

public class ExitAdapter implements ExitPort {
    @Override
    public void exitGame() {
        System.exit(0);
    }
}
