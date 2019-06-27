module eu.kgorecki.rpgame.engine {
    requires java.base;

    requires eu.kgorecki.rpgame.userinterface;
    requires eu.kgorecki.rpgame.enemy;
    requires eu.kgorecki.rpgame.character;
    requires eu.kgorecki.rpgame.world;
    requires eu.kgorecki.rpgame.commands;

    exports eu.kgorecki.rpgame.engine;
}