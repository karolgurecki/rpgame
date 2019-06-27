package eu.kgorecki.rpgame.commands.application.commands

import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.commands.application.ports.CharacterPort
import eu.kgorecki.rpgame.commands.application.ports.EnemyPort
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort
import eu.kgorecki.rpgame.commands.application.ports.WorldPort
import eu.kgorecki.rpgame.enemy.dto.EnemyId
import spock.lang.Specification

class AttackCommandSpec extends Specification {
    
    def mockedEnemyPort = Mock(EnemyPort)
    def mockedCharacterPort = Mock(CharacterPort)
    def mockedWorldPort = Mock(WorldPort)
    def mockedUserInteractionPort = Mock(UserInteractionPort)
    
    def sut = new AttackCommand(mockedEnemyPort, mockedCharacterPort, mockedWorldPort, mockedUserInteractionPort)
    
    def "should attack enemy"() {
        given:
            def enemyId = EnemyId.of(0)
            def characterId = CharacterId.of(UUID.randomUUID())
            def attackPower = 2
            
            mockedWorldPort.findEnemyInCurrentRoom() >> Optional.of(enemyId)
            mockedEnemyPort.isAlive(enemyId) >> true
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            
            mockedCharacterPort.isAlive(characterId) >> true
            mockedCharacterPort.findAttackPower(characterId) >> Optional.of(attackPower)
        
        when:
            sut.execute()
        
        then:
            1 * mockedEnemyPort.takeDamage(attackPower, enemyId)
    }
    
    def "character should gain experience when he attacks an enemy"() {
        given:
            def enemyId = EnemyId.of(0)
            def characterId = CharacterId.of(UUID.randomUUID())
            def attackPower = 2
            
            mockedWorldPort.findEnemyInCurrentRoom() >> Optional.of(enemyId)
            mockedEnemyPort.isAlive(enemyId) >> true
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            
            mockedCharacterPort.isAlive(characterId) >> true
            mockedCharacterPort.findAttackPower(characterId) >> Optional.of(attackPower)
        
        when:
            sut.execute()
        
        then:
            1 * mockedCharacterPort.gainExperience(characterId, _ as Integer)
    }
    
    def "should not attack any enemy when not exists in current room"() {
        given:
            mockedWorldPort.findEnemyInCurrentRoom() >> Optional.empty()
        
        when:
            sut.execute()
        
        then:
            0 * mockedEnemyPort.takeDamage(_ as Integer, _ as EnemyId)
    }
    
    def "should not attack any enemy when character not exists in the world"() {
        given:
            def enemyId = EnemyId.of(0)
            
            mockedWorldPort.findEnemyInCurrentRoom() >> Optional.of(enemyId)
            mockedEnemyPort.isAlive(enemyId) >> true
    
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.empty()
        
        when:
            sut.execute()
        
        then:
            0 * mockedEnemyPort.takeDamage(_ as Integer, _ as EnemyId)
    }
    
    def "should not attack any enemy when enemy in given room is dead"() {
        given:
            def enemyId = EnemyId.of(0)
            
            mockedWorldPort.findEnemyInCurrentRoom() >> Optional.of(enemyId)
            mockedEnemyPort.isAlive(enemyId) >> false
        
        when:
            sut.execute()
        
        then:
            0 * mockedEnemyPort.takeDamage(_ as Integer, _ as EnemyId)
    }
    
    def "should not attack any enemy when character is not alive"() {
        given:
            def enemyId = EnemyId.of(0)
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findEnemyInCurrentRoom() >> Optional.of(enemyId)
            mockedEnemyPort.isAlive(enemyId) >> true
    
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            mockedCharacterPort.isAlive(characterId) >> false
        
        when:
            sut.execute()
        
        then:
            0 * mockedEnemyPort.takeDamage(_ as Integer, _ as EnemyId)
    }
}
