package eu.kgorecki.rpgame.commands.application.commands

import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.commands.application.ports.CharacterPort
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort
import eu.kgorecki.rpgame.commands.application.ports.WorldPort
import eu.kgorecki.rpgame.items.dto.ItemId
import spock.lang.Specification

class TakeItemCommandSpec extends Specification {
    
    def mockedCharacterPort = Mock(CharacterPort)
    def mockedWorldPort = Mock(WorldPort)
    def mockedUserInteractionPort = Mock(UserInteractionPort)
    
    def sut = new TakeItemCommand(mockedCharacterPort, mockedWorldPort, mockedUserInteractionPort)
    
    def "should take item "() {
        given:
            def itemId = ItemId.of(0)
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findItemInCurrentRoom() >> Optional.of(itemId)
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            
            mockedCharacterPort.isAlive(characterId) >> true
        when:
            sut.execute()
        
        then:
            1 * mockedCharacterPort.equipItem(characterId, itemId)
    }
    
    def "should not take item when not exists in current room"() {
        given:
            mockedWorldPort.findItemInCurrentRoom() >> Optional.empty()
        
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.equipItem(_ as CharacterId, _ as ItemId)
    }
    
    def "should not take item when character not exists in the world"() {
        given:
            def itemId = ItemId.of(0)
            
            mockedWorldPort.findItemInCurrentRoom() >> Optional.of(itemId)
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.empty()
        
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.equipItem(_ as CharacterId, _ as ItemId)
    }
    
    def "should not take item when character is not alive"() {
        given:
            def itemId = ItemId.of(0)
            def characterId = CharacterId.of(UUID.randomUUID())
            
            mockedWorldPort.findItemInCurrentRoom() >> Optional.of(itemId)
            
            mockedWorldPort.findCharacterPresentInWorld() >> Optional.of(characterId)
            mockedCharacterPort.isAlive(characterId) >> false
        
        when:
            sut.execute()
        
        then:
            0 * mockedCharacterPort.equipItem(_ as CharacterId, _ as ItemId)
    }
}
