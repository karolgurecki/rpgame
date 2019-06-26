package eu.kgorecki.rpgame.world.domain;

import eu.kgorecki.rpgame.world.dto.MoveDirection;

public enum Direction {
    LEFT, RIGTH, AHEAD, BACK;

    static Direction from(MoveDirection moveDirection) {
        return valueOf(moveDirection.name());
    }
}
