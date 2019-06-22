package eu.kgorecki.rpgame.userinterface

import eu.kgorecki.rpgame.userinterface.application.UserInputPort
import spock.lang.Specification
import spock.lang.Unroll

class UserInterfaceFacadeSpec extends Specification {
    
    @Unroll
    def "should give '#text' as user input"() {
        given:
            def userInputPort = new UserInputPortForTest()
    
            userInputPort.inputText = text
    
            def sut = UserInterfaceFacadeFactory.createFacade(null, userInputPort)
        
        expect:
            sut.getUserInput() == text
        
        where:
            text << ['test', 'my text', 'It works']
    }
    
    @Unroll
    def "should give '#text' as user input and output '#textToDisplay'"() {
        given:
            def userInputPort = new UserInputPortForTest()
            
            userInputPort.inputText = text
            
            def sut = UserInterfaceFacadeFactory.createFacade(null, userInputPort)
        
        when:
            def result = sut.getUserInputWithTextInTheSameLine(textToDisplay)
        
        then:
            result == text
            userInputPort.textToDisplay == textToDisplay
        
        where:
            text << ['test', 'my text', 'It works']
            textToDisplay << ['display one', 'display two', 'display tree']
    }
    
    @Unroll
    def "should display '#text'"() {
        given:
            def displayedText
            def sut = UserInterfaceFacadeFactory.createFacade({ text -> displayedText = text }, null)
        
        when:
            sut.displayText(text)
        
        then:
            displayedText == text
        
        where:
            text << ['test', 'my text', 'It works']
    }
    
    class UserInputPortForTest implements UserInputPort {
        
        def inputText
        def textToDisplay
        
        @Override
        String getInputFromUser() {
            return inputText
        }
        
        @Override
        String getUserInputWithTextInTheSameLine(String text) {
            textToDisplay = text
            
            return inputText
        }
    }
}
