package eu.kgorecki.rpgame.character

import eu.kgorecki.rpgame.character.domain.Character
import eu.kgorecki.rpgame.character.domain.LoadPort
import eu.kgorecki.rpgame.character.domain.SavePort
import eu.kgorecki.rpgame.character.domain.UserInteractionPort
import eu.kgorecki.rpgame.character.domain.UserMassages
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus
import eu.kgorecki.rpgame.character.infrastructure.SingleCharacterRepositoryAdapter
import spock.lang.Specification

class CharacterFacadeSpec extends Specification {
    def mockedUserInterfaceFacade = Mock(UserInteractionPort)
    def repositoryAdapter = new SingleCharacterRepositoryAdapter()
    def mockedSavePort = Mock(SavePort)
    def mockedLoadPort = Mock(LoadPort)
    
    def sut = CharacterFacadeFactory.createFacade(repositoryAdapter, mockedUserInterfaceFacade, mockedSavePort, mockedLoadPort)
    
    def "should create character if one not exists"() {
        when:
            def resultStatus = sut.createCharacter()
        
        then:
            resultStatus == CharacterCreationStatus.CREATED
            
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_NAME) >> 'test name'
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SKIN_COLOR) >> 'test skin'
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_JOB) >> 'test job'
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SEX) >> 'test sex'
            
            with(repositoryAdapter.character) {
                name == 'test name'
                skinColor == 'test skin'
                job == 'test job'
                sex == 'test sex'
            }
    }
    
    def "should return already create status when character already exists"() {
        given:
            repositoryAdapter.character = new Character('', '', '', '')
        
        when:
            def resultStatus = sut.createCharacter()
        
        then:
            resultStatus == CharacterCreationStatus.ALREADY_CREATED
        
        cleanup:
            repositoryAdapter.character = null
    }
    
    def "should save character if it is loaded"() {
        
        given:
            def character = new Character('', '', '', '')
            repositoryAdapter.character = character
        
        when:
            sut.saveCharacters()
        
        then:
            1 * mockedSavePort.save(character)
        
        cleanup:
            repositoryAdapter.character = null
    }
    
    def "should not try to save character if one is not loaded"() {
        given:
            repositoryAdapter.character = null
        
        when:
            sut.saveCharacters()
        
        then:
            0 * mockedSavePort.save(_ as Character)
    }
    
    def "should load character data to repository"() {
        given:
            def dut = new Character('test', 'test', '', '')
        
        when:
            sut.loadCharacter()
        
        then:
            1 * mockedLoadPort.load() >> Optional.of(dut)
            repositoryAdapter.character == dut
    }
    
    def "should not override existing data in character repository when character was not loaded"() {
        given:
            def dut = new Character('test', 'test', '', '')
            repositoryAdapter.character = dut
            mockedLoadPort.load() >> Optional.empty()
        
        when:
            sut.loadCharacter()
        
        then:
            repositoryAdapter.character == dut
        
    }
}
