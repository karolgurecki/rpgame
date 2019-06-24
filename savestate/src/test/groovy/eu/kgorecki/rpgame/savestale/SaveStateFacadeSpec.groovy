package eu.kgorecki.rpgame.savestale

import eu.kgorecki.rpgame.savestale.application.GameStateLoaderPort
import eu.kgorecki.rpgame.savestale.application.GameStateSaverPort
import eu.kgorecki.rpgame.savestale.application.UserInteractionPort
import eu.kgorecki.rpgame.savestale.infrastructure.CannotLoadGameStateException
import eu.kgorecki.rpgame.savestale.infrastructure.CannotSaveGameStateException
import eu.kgorecki.rpgame.savestale.infrastructure.GameStateNotExistsException
import spock.lang.Specification

class SaveStateFacadeSpec extends Specification {
    
    def mockedUserInteractionPort = Mock(UserInteractionPort)
    def mockedGameStateSaverPort = Mock(GameStateSaverPort)
    def mockedGameStateLoaderPort = Mock(GameStateLoaderPort)
    def sut = SaveStateFacadeFactory.createFacade(mockedUserInteractionPort, mockedGameStateSaverPort, mockedGameStateLoaderPort)
    
    
    def "should save String 'test' and give saved state name 'String'"() {
        given:
            def objectToSave = 'test'
        
        when:
            sut.save(objectToSave)
        
        then:
            1 * mockedGameStateSaverPort.saveGameState(objectToSave, objectToSave.class.simpleName)
    }
    
    def "should not thrown any exceptions when save state was not be able to be saved"() {
        given:
            def objectToSave = 'test'
    
            mockedGameStateSaverPort.saveGameState(objectToSave, objectToSave.class.simpleName) >> {
                throw new CannotSaveGameStateException()
            }
        
        when:
            sut.save(objectToSave)
        
        then:
            notThrown()
    }
    
    def "should load save stale based on class of the savved object"() {
        given:
            mockedGameStateLoaderPort.loadGameState('String', String) >> Optional.of('test')
        
        when:
            def result = sut.load(String)
        
        then:
            result.filter({ str -> str == 'test' }).isPresent()
    }
    
    def "should return empty Optional when save state was not be able to be load"() {
        given:
            mockedGameStateLoaderPort.loadGameState('String', String) >> { throw new CannotLoadGameStateException() }
        expect:
            sut.load(String) == Optional.empty()
    }
    
    def "should return empty Optional when save state not exists"() {
        given:
            mockedGameStateLoaderPort.loadGameState('String', String) >> { throw new GameStateNotExistsException() }
    
        expect:
            sut.load(String) == Optional.empty()
    }
}
