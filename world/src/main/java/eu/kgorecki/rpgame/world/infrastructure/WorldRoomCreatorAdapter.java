package eu.kgorecki.rpgame.world.infrastructure;

import eu.kgorecki.rpgame.enemy.EnemyFacade;
import eu.kgorecki.rpgame.items.ItemFacade;
import eu.kgorecki.rpgame.world.domain.Direction;
import eu.kgorecki.rpgame.world.domain.Room;
import eu.kgorecki.rpgame.world.domain.WorldRoomCreatorPort;

import java.util.Map;

import static java.util.Collections.emptyMap;

public class WorldRoomCreatorAdapter implements WorldRoomCreatorPort {

    private final EnemyFacade enemyFacade;
    private final ItemFacade itemFacade;

    public WorldRoomCreatorAdapter(EnemyFacade enemyFacade, ItemFacade itemFacade) {
        this.enemyFacade = enemyFacade;
        this.itemFacade = itemFacade;
    }

    @Override
    public Room createRooms() {
        return createMap();
    }

    private Room createMap() {
        Room endRoom = Room.roomWithEnemy("Final room with enemy;) After that you win.",
                enemyFacade.findRandomEnemy().get(), emptyMap());

        Room roomBeforeTheEnd = Room.emptyRoom("The end is near. Turn left and defeat last enemy",
                Map.of(Direction.LEFT, endRoom));
        Room roomBeforeWithItemTheEnd = Room.roomWithItem(
                "The end is near. You want to go ahead and defeat last enemy. But you see chest. Will you take it?",
                itemFacade.getRandomItem().get(), Map.of(Direction.AHEAD, endRoom));

        Room secondRoom = Room.emptyRoom("Now you see fork in the road. Do you go left or right",
                Map.of(Direction.LEFT, roomBeforeTheEnd, Direction.RIGTH, roomBeforeWithItemTheEnd));

        Room firstRoom = Room.roomWithEnemy("First room and first enemy. You need to defeat it before you can go left",
                enemyFacade.findRandomEnemy().get(), Map.of(Direction.LEFT, secondRoom));

        return Room.roomWithItem("You are standing at entrance to a cave. At the wall you see a item",
                itemFacade.getRandomItem().get(), Map.of(Direction.AHEAD, firstRoom));
    }
}
