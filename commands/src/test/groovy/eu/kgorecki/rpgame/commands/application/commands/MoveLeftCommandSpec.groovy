package eu.kgorecki.rpgame.commands.application.commands

import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.commands.application.ports.CharacterPort
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort
import eu.kgorecki.rpgame.commands.application.ports.WorldPort
import spock.lang.Specification

class MoveLeftCommandSpec extends Specification {
    def mockedCharacterPort = Mock(CharacterPort)
    def mockedWorldPort = Mock(WorldPort)
    def mockedUserInteractionPort = Mock(UserInteractionPort)
    
    def sut = new MoveLeftCommand(mockedCharacterPort, mockedWorldPort, mockedUserInteractionPort)
    
    def "should move character to left when character exists and his is alive"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            
            mockedCharacterPort.isAlive(characterId) >> true
        
        when:
            sut.execute()
        
        then:
            1 * mockedCharacterPort.moveLeft(characterId)
    }
    
    def "should not move character to left when character exists but his is dead"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            
            mockedCharacterPort.isAlive(characterId) >> false
        
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.moveLeft(characterId)
    }
    
    def "should not move character to left when character not exists"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.empty()
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.moveLeft(characterId)
    }
}
