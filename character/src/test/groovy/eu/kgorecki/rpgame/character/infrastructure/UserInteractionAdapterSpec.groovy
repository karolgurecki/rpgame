package eu.kgorecki.rpgame.character.infrastructure


import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory
import spock.lang.Specification
import spock.lang.Unroll

class UserInteractionAdapterSpec extends Specification {
    
    def NEW_LINE = '\r\n'
    def sut = new UserInteractionAdapter(UserInterfaceFacadeFactory.createFacade())
    
    @Unroll
    def "should display '#text'"() {
        given:
            def outStream = new ByteArrayOutputStream()
            System.setOut(new PrintStream(outStream))
        
        when:
            sut.displayText(text)
        
        then:
            outStream.toByteArray() == (text + NEW_LINE).getBytes()
        
        where:
            text << ['test', 'my text', 'It works']
    }
    
    
    @Unroll
    def "should give '#text' as user input and message in the same line"() {
        given:
            def inputStream = new ByteArrayInputStream(text.getBytes())
            System.setIn(inputStream)
        
        expect:
            sut.getUserInputWithTextInTheSameLine('test: ') == text
        
        where:
            text << ['test', 'my text', 'It works']
    }
}
