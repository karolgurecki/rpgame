package eu.kgorecki.rpgame.userinterface.infrastructure


import spock.lang.Specification
import spock.lang.Unroll

class UserInputFromSystemInAdapterSpec extends Specification {
    
    @Unroll
    def "should give '#text' as user input"() {
        given:
            def inputStream = new ByteArrayInputStream(text.getBytes())
            System.setIn(inputStream)
        
        and:
            def sut = new UserInputFromSystemInAdapter()
        
        expect:
            sut.getInputFromUser() == text
        
        where:
            text << ['test', 'my text', 'It works']
    }
    @Unroll
    def "should give '#text' as user input and message in the same line"() {
        given:
            def inputStream = new ByteArrayInputStream(text.getBytes())
            System.setIn(inputStream)
        
        and:
            def sut = new UserInputFromSystemInAdapter()
        
        expect:
            sut.getUserInputWithTextInTheSameLine('test: ') == text
        
        where:
            text << ['test', 'my text', 'It works']
    }
}
