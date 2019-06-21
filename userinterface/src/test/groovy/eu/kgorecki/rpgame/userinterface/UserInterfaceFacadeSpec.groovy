package eu.kgorecki.rpgame.userinterface


import spock.lang.Specification
import spock.lang.Unroll

class UserInterfaceFacadeSpec extends Specification {
    
    @Unroll
    def "should give '#text' as user input"() {
        given:
            def sut = UserInterfaceFactory.createFacade(null, { text })
        
        expect:
            sut.getUserInput() == text
        
        where:
            text << ['test', 'my text', 'It works']
    }
    
    @Unroll
    def "should display '#text'"() {
        given:
            def displayedText
            def sut = UserInterfaceFactory.createFacade({ text -> displayedText = text }, null)
        
        when:
            sut.displayText(text)
        
        then:
            displayedText == text
        
        where:
            text << ['test', 'my text', 'It works']
    }
}
