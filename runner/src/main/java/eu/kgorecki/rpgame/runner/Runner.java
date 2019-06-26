package eu.kgorecki.rpgame.runner;

import eu.kgorecki.rpgame.engine.GameEngineFacadeFactory;

public class Runner {
    public static void main(String[] args) {
        GameEngineFacadeFactory.createFacade().runGame();
    }
}
