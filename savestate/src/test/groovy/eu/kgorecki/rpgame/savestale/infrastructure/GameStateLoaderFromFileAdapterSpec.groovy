package eu.kgorecki.rpgame.savestale.infrastructure

import org.apache.commons.io.FileUtils
import spock.lang.Specification

class GameStateLoaderFromFileAdapterSpec extends Specification {
    
    def "should load save state if file exists"() {
        given:
            new GameStateSaverToFileAdapter().saveGameState('test text', 'String')
            
            def sut = new GameStateLoaderFromFileAdapter()
        
        when:
            def result = sut.loadGameState('String', String)
        
        then:
            result.filter({ str -> str == 'test text' }).isPresent()
        
        cleanup:
            def saveStateFilePath = new SavePathResolver().getSaveStateFilePath('')
            
            FileUtils.deleteDirectory(new File(saveStateFilePath).getParentFile())
    }
    
    def "should throw GameStateNotExistsException when trying to load save state for which file do not exists"() {
        given:
            def sut = new GameStateLoaderFromFileAdapter()
        
        when:
            sut.loadGameState('String', String)
        
        then:
            thrown GameStateNotExistsException
    }
}
