package eu.kgorecki.rpgame.character

import eu.kgorecki.rpgame.character.domain.Character
import eu.kgorecki.rpgame.character.domain.UserMassages
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus
import eu.kgorecki.rpgame.character.infrastructure.SingleCharacterRepositoryAdapter
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade
import spock.lang.Specification

class CharacterFacadeSpec extends Specification {
    
    def "should create character if one not exists"() {
        given:
            def mockedUserInterfaceFacade = Mock(UserInterfaceFacade)
            def repositoryAdapter = new SingleCharacterRepositoryAdapter()
            
            def sut = CharacterFacadeFactory.createFacade(repositoryAdapter, mockedUserInterfaceFacade)
        
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
            def mockedUserInterfaceFacade = Mock(UserInterfaceFacade)
            def repositoryAdapter = new SingleCharacterRepositoryAdapter()
            
            repositoryAdapter.character = new Character('', '', '', '')
            
            def sut = CharacterFacadeFactory.createFacade(repositoryAdapter, mockedUserInterfaceFacade)
        
        when:
            def resultStatus = sut.createCharacter()
        
        then:
            resultStatus == CharacterCreationStatus.ALREADY_CREATED
        
        cleanup:
            repositoryAdapter.character = null
    }
    
}
