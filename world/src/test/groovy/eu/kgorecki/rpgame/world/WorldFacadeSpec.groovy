package eu.kgorecki.rpgame.world

import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.enemy.dto.EnemyId
import eu.kgorecki.rpgame.items.dto.ItemId
import eu.kgorecki.rpgame.world.domain.*
import eu.kgorecki.rpgame.world.dto.CreateWorldCommand
import eu.kgorecki.rpgame.world.dto.MoveCharacterCommand
import spock.lang.Specification

class WorldFacadeSpec extends Specification {
    def mockedRepositoryPort = Mock(RepositoryPort)
    def mockedSaverPort = Mock(SaverPort)
    def mockedLoaderPort = Mock(LoaderPort)
    def mockedUserInteractionPort = Mock(UserInteractionPort)
    def mockedWorldRoomCreatorPort = Mock(WorldRoomCreatorPort)

    def sut = WorldFacadeFactory.createFacade(mockedRepositoryPort, mockedSaverPort, mockedLoaderPort, mockedUserInteractionPort,
            mockedWorldRoomCreatorPort)

    def "should create game world"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def command = new CreateWorldCommand(characterId)

            def roomForTest = Room.roomWithEnemy(EnemyId.of(1), Collections.emptyMap())
            mockedWorldRoomCreatorPort.createRooms() >> roomForTest

        when:
            sut.createTheWorld(command)

        then:
            1 * mockedRepositoryPort.save(new World(characterId, roomForTest))
    }

    def "should return optional with CharacterId when character exists in world"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def roomForTest = Room.roomWithEnemy(EnemyId.of(1), Collections.emptyMap())

            mockedRepositoryPort.findWorld() >> Optional.of(new World(characterId, roomForTest))

        expect:
            sut.findCharacter() == Optional.of(characterId)
    }

    def "should return empty optional when character do not exists in world"() {
        given:
            mockedRepositoryPort.findWorld() >> Optional.empty()

        expect:
            sut.findCharacter() == Optional.empty()
    }

    def "should return optional with EnemyId when enemy exists in current room"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def enemyId = EnemyId.of(1)
            def roomForTest = Room.roomWithEnemy(enemyId, Collections.emptyMap())

            mockedRepositoryPort.findWorld() >> Optional.of(new World(characterId, roomForTest))

        expect:
            sut.findEnemyInCurrentRoom() == Optional.of(enemyId)
    }

    def "should return optional with ItemId when item exists in current room"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def itemId = ItemId.of(1)
            def roomForTest = Room.roomWithItem(itemId, Collections.emptyMap())

            mockedRepositoryPort.findWorld() >> Optional.of(new World(characterId, roomForTest))

        expect:
            sut.findItemInCurrentRoom() == Optional.of(itemId)
    }

    def "should return empty optional when enemy do not exists in current room"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def itemId = ItemId.of(1)
            def roomForTest = Room.roomWithItem(itemId, Collections.emptyMap())

            mockedRepositoryPort.findWorld() >> Optional.of(new World(characterId, roomForTest))

        expect:
            sut.findEnemyInCurrentRoom() == Optional.empty()
    }

    def "should return empty optional when item do not exists in current room"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def enemyId = EnemyId.of(1)
            def roomForTest = Room.roomWithEnemy(enemyId, Collections.emptyMap())

            mockedRepositoryPort.findWorld() >> Optional.of(new World(characterId, roomForTest))

        expect:
            sut.findItemInCurrentRoom() == Optional.empty()
    }

    def "should move character to another room in direction indicated"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def enemyId = EnemyId.of(1)

        and: 'build the world'
            def secondRoom = Room.roomWithEnemy(enemyId, Collections.emptyMap())
            def startingRoom = Room.roomWithEnemy(enemyId, Map.of(Direction.AHEAD, secondRoom))

            mockedRepositoryPort.findWorld() >> Optional.of(new World(characterId, startingRoom))

        when:
            sut.moveCharacter(MoveCharacterCommand.MOVE_AHEAD)

        then:
            1 * mockedRepositoryPort.save(new World(characterId, secondRoom))
    }

    def "should display waring to the user when trying character to another room in direction in which"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def enemyId = EnemyId.of(1)

        and: 'build the world'
            def secondRoom = Room.roomWithEnemy(enemyId, Collections.emptyMap())
            def startingRoom = Room.roomWithEnemy(enemyId, Map.of(Direction.AHEAD, secondRoom))

            mockedRepositoryPort.findWorld() >> Optional.of(new World(characterId, startingRoom))

        when:
            sut.moveCharacter(MoveCharacterCommand.MOVE_LEFT)

        then:
            1 * mockedUserInteractionPort.displayText(_ as String)
    }

    def "should save world state"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def enemyId = EnemyId.of(1)
            def roomForTest = Room.roomWithEnemy(enemyId, Collections.emptyMap())

            def world = new World(characterId, roomForTest)
            mockedRepositoryPort.findWorld() >> Optional.of(world)

        when:
            sut.saveState()

        then:
            1 * mockedSaverPort.save(world)
    }

    def "should load world state"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            def enemyId = EnemyId.of(1)
            def roomForTest = Room.roomWithEnemy(enemyId, Collections.emptyMap())

            def world = new World(characterId, roomForTest)
            mockedLoaderPort.load() >> Optional.of(world)

        when:
            sut.loadState()

        then:
            1 * mockedRepositoryPort.save(world)
    }
}
