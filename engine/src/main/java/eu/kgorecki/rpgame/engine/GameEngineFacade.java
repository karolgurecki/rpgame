package eu.kgorecki.rpgame.engine;

import eu.kgorecki.rpgame.engine.application.Service;

public class GameEngineFacade {

    private final Service service;

    GameEngineFacade(Service service) {
        this.service = service;
    }

    public void runGame(){
        service.runGame();
    }
}
