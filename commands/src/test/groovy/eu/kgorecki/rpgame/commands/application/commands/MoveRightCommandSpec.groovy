package eu.kgorecki.rpgame.commands.application.commands

import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.commands.ports.CharacterPort
import eu.kgorecki.rpgame.commands.ports.UserInteractionPort
import eu.kgorecki.rpgame.commands.ports.WorldPort
import spock.lang.Specification

class MoveRightCommandSpec extends Specification {
    def mockedCharacterPort = Mock(CharacterPort)
    def mockedWorldPort = Mock(WorldPort)
    def mockedUserInteractionPort = Mock(UserInteractionPort)
    
    def sut = new MoveRightCommand(mockedCharacterPort, mockedWorldPort, mockedUserInteractionPort)
    
    def "should move character to right when character exists and his is alive"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            
            mockedCharacterPort.isAlive(characterId) >> true
        
        when:
            sut.execute()
        
        then:
            1 * mockedCharacterPort.moveRight(characterId)
    }
    
    def "should not move character to right when character exists but his is dead"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            
            mockedCharacterPort.isAlive(characterId) >> false
        
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.moveRight(characterId)
    }
    
    def "should not move character to right when character not exists"() {
        given:
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.empty()
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.moveRight(characterId)
    }
}
