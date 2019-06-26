package eu.kgorecki.rpgame.world.domain;

import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.world.dto.MoveCharacterCommand;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Room {
    private static final String PATH_NOT_FOUND_MESSAGE = "You can not move in this direction.";

    private final ItemId item;
    private final EnemyId enemy;
    private final Map<Direction, Room> paths;

    private Room(ItemId item, EnemyId enemy, Map<Direction, Room> paths) {
        this.item = item;
        this.enemy = enemy;
        this.paths = paths;
    }

    public static Room roomWithItem(ItemId item, Map<Direction, Room> paths) {
        return new Room(item, null, paths);
    }

    public static Room roomWithEnemy(EnemyId enemy, Map<Direction, Room> paths) {
        return new Room(null, enemy, paths);
    }

    ItemId getItem() {
        return item;
    }

    EnemyId getEnemy() {
        return enemy;
    }

    Optional<Room> move(MoveCharacterCommand command, UserInteractionPort userInteractionPort) {
        Direction directionToMove = Direction.from(command.getDirection());

        Room path = paths.get(directionToMove);

        if (Objects.isNull(path)) {
            userInteractionPort.displayText(PATH_NOT_FOUND_MESSAGE);
        }

        return Optional.ofNullable(path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(item, room.item) &&
                Objects.equals(enemy, room.enemy) &&
                paths.equals(room.paths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, enemy, paths);
    }
}
