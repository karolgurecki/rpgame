module eu.kgorecki.rpgame.world {
    requires java.base;

    requires eu.kgorecki.rpgame.character;
    requires eu.kgorecki.rpgame.enemy;
    requires eu.kgorecki.rpgame.items;
    requires eu.kgorecki.rpgame.savestale;
    requires eu.kgorecki.rpgame.userinterface;

    exports eu.kgorecki.rpgame.world;
    exports eu.kgorecki.rpgame.world.dto;
}