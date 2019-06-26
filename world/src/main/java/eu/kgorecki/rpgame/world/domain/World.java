package eu.kgorecki.rpgame.world.domain;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.enemy.dto.EnemyId;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.world.dto.MoveCharacterCommand;

import java.util.Objects;

public class World {
    private final CharacterId character;
    private final Room currentRoom;

    World(CharacterId character, Room currentRoom) {
        this.character = character;
        this.currentRoom = currentRoom;
    }

    World changeRoom(Room newRoom) {
        return new World(character, newRoom);
    }

    CharacterId getCharacter() {
        return character;
    }


    ItemId getItemFromCurrentRoom() {
        return currentRoom.getItem();
    }

    EnemyId getEnemyFromCurrentRoom() {
        return currentRoom.getEnemy();
    }

    World moveCharacter(MoveCharacterCommand command, UserInteractionPort userInteractionPort) {
        return currentRoom.move(command, userInteractionPort)
                .map(this::changeRoom)
                .orElse(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return Objects.equals(character, world.character) &&
                Objects.equals(currentRoom, world.currentRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(character, currentRoom);
    }
}