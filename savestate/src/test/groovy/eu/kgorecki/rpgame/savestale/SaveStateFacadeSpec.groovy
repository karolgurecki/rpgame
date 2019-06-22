package eu.kgorecki.rpgame.savestale

import eu.kgorecki.rpgame.savestale.infrastructure.CannotLoadGameStateException
import eu.kgorecki.rpgame.savestale.infrastructure.CannotSaveGameStateException
import eu.kgorecki.rpgame.savestale.infrastructure.GameStateNotExistsException
import eu.kgorecki.rpgame.userinterface.UserInterfaceFactory
import spock.lang.Specification

class SaveStateFacadeSpec extends Specification {
    
    def "should save String 'test' and give saved state name 'String'"() {
        given:
            def objectAttemptedToSave
            
            def sut = SaveStateFactory.createFacade(null, { object, fileName ->
                objectAttemptedToSave = object
            }, null)
        
        when:
            sut.save('test')
        
        then:
            objectAttemptedToSave == 'test'
    }
    
    def "should not thrown any exceptions when save state was not be able to be saved"() {
        given:
            def sut = SaveStateFactory.createFacade(UserInterfaceFactory.createFacade(), { object, fileName ->
                throw new CannotSaveGameStateException()
            }, null)
        
        when:
            sut.save('test')
        
        then:
            notThrown()
    }
    
    def "should load save stale based on class of the savved object"() {
        given:
            def classOfObjectAttemptedTobeLoad
            def sut = SaveStateFactory.createFacade(UserInterfaceFactory.createFacade(), null, { name, clazz ->
                classOfObjectAttemptedTobeLoad = clazz
                Optional.of('test')
            })
        
        when:
            def result = sut.load(String)
        
        then:
            result.filter({ str -> str == 'test' }).isPresent()
            classOfObjectAttemptedTobeLoad == String
    }
    
    def "should return empty Optional when save state was not be able to be load"() {
        given:
            def sut = SaveStateFactory.createFacade(UserInterfaceFactory.createFacade(), null, { name, clazz -> throw new CannotLoadGameStateException() })
        
        expect:
            sut.load(String) == Optional.empty()
    }
    
    def "should return empty Optional when save state not exists"() {
        given:
            def sut = SaveStateFactory.createFacade(UserInterfaceFactory.createFacade(), null, { name, clazz -> throw new GameStateNotExistsException() })
        
        expect:
            sut.load(String) == Optional.empty()
    }
}
