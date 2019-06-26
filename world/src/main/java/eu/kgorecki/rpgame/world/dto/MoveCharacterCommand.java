package eu.kgorecki.rpgame.world.dto;

public enum MoveCharacterCommand {
    MOVE_AHEAD(MoveDirection.AHEAD),
    MOVE_LEFT(MoveDirection.LEFT),
    MOVE_RIGTH(MoveDirection.RIGTH),
    MOVE_BACK(MoveDirection.BACK);

    private final MoveDirection direction;

    MoveCharacterCommand(MoveDirection direction) {
        this.direction = direction;
    }

    public MoveDirection getDirection() {
        return direction;
    }
}
