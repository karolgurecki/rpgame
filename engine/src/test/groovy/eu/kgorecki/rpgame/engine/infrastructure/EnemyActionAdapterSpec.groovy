package eu.kgorecki.rpgame.engine.infrastructure

import eu.kgorecki.rpgame.character.CharacterFacade
import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand
import eu.kgorecki.rpgame.enemy.EnemyFacade
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPower
import eu.kgorecki.rpgame.enemy.dto.EnemyAttackPowerQuery
import eu.kgorecki.rpgame.enemy.dto.EnemyId
import eu.kgorecki.rpgame.world.WorldFacade
import spock.lang.Specification

class EnemyActionAdapterSpec extends Specification {
    def mockedEnemyFacade = Mock(EnemyFacade)
    def mockedCharacterFacade = Mock(CharacterFacade)
    def mockedWorldFacade = Mock(WorldFacade)

    def sut = new EnemyActionAdapter(mockedEnemyFacade, mockedCharacterFacade, mockedWorldFacade)

    def "should tell to character to take damage when enemy exists in room"() {
        given:
            def enemyId = EnemyId.of(1)
            def characterId = CharacterId.of(UUID.randomUUID())

            mockedWorldFacade.findCharacter() >> Optional.of(characterId)
            mockedWorldFacade.findEnemyInCurrentRoom() >> Optional.of(enemyId)
            mockedEnemyFacade.findAttackPower(new EnemyAttackPowerQuery(enemyId)) >> Optional.of(new EnemyAttackPower(1))

        when:
            sut.executeEnemyTurn()

        then:
            1 * mockedCharacterFacade.takeDamage(new CharacterTakeDamageCommand(characterId, 1))
    }

    def "should not try to attack character when enemy not exists in room"() {
        given:
            mockedWorldFacade.findEnemyInCurrentRoom() >> Optional.empty()

        when:
            sut.executeEnemyTurn()

        then:
            0 * mockedCharacterFacade.takeDamage(_ as CharacterTakeDamageCommand)
    }
}
