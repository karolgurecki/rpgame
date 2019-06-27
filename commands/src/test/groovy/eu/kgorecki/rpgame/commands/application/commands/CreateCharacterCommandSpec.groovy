package eu.kgorecki.rpgame.commands.application.commands

import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.commands.application.CharacterPort
import eu.kgorecki.rpgame.commands.application.UserInteractionPort
import eu.kgorecki.rpgame.commands.application.WorldPort
import spock.lang.Specification

class CreateCharacterCommandSpec extends Specification {
    
    def mockedCharacterPort = Mock(CharacterPort)
    def mockedWorldPort = Mock(WorldPort)
    def mockedUserInteractionPort = Mock(UserInteractionPort)
    
    def sut = new CreateCharacterCommand(mockedWorldPort, mockedCharacterPort, mockedUserInteractionPort)
    
    def "should create character and put him in the world when one not exists in world"() {
        given:
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.empty()
            def characterId = CharacterId.of(UUID.randomUUID())
        
        when:
            sut.execute()
        
        then:
            1 * mockedCharacterPort.create() >> Optional.of(characterId)
            1 * mockedWorldPort.putCharacterInTheWorld(characterId)
    }
    
    def "should not try create character when one already exists in world"() {
        given:
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(CharacterId.of(UUID.randomUUID()))
        
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.create()
    }
}
