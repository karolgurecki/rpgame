package eu.kgorecki.rpgame.savestale.infrastructure

import org.apache.commons.io.FileUtils
import spock.lang.Specification

class GameStateSaverToFileAdapterSpec extends Specification {
    def gameSateLoader = new GameStateLoaderFromFileAdapter()
    def sut = new GameStateSaverToFileAdapter()
    def savePathResolver = new SavePathResolver()
    
    def "should save state to file"() {
        given:
            def dut = 'test text top be saved'
            def saveStateFileName = 'TestString'
        
        when:
            sut.saveGameState(dut, saveStateFileName)
        
        then:
            def loadedObject = gameSateLoader.loadGameState(saveStateFileName, String)
            
            loadedObject.filter({ str -> str == dut }).isPresent()
        
        cleanup:
            def saveStateFilePath = savePathResolver.getSaveStateFilePath('')
            
            FileUtils.deleteDirectory(new File(saveStateFilePath).getParentFile())
    }
    
    def "should override existing save state file if one already exists"() {
        given:
            def dut = 'test text top be saved'
            def saveStateFileName = 'TestString'
            
            sut.saveGameState('other content', saveStateFileName)
        when:
            sut.saveGameState(dut, saveStateFileName)
        
        then:
            def loadedObject = gameSateLoader.loadGameState(saveStateFileName, String)
            
            loadedObject.filter({ str -> str == dut }).isPresent()
        
        cleanup:
            def saveStateFilePath = savePathResolver.getSaveStateFilePath('')
            
            FileUtils.deleteDirectory(new File(saveStateFilePath).getParentFile())
    }
}
