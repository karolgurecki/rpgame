module eu.kgorecki.rpgame.character {
    requires java.base;

    requires eu.kgorecki.rpgame.userinterface;
    requires eu.kgorecki.rpgame.items;
    requires eu.kgorecki.rpgame.savestale;

    exports eu.kgorecki.rpgame.character;
    exports eu.kgorecki.rpgame.character.dto;
}