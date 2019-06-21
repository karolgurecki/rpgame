package eu.kgorecki.rpgame.userinterface.infrastructure

import eu.kgorecki.rpgame.userinterface.UserInterfaceFactory
import spock.lang.Specification
import spock.lang.Unroll

class ToConsoleDisplayAdapterSpec extends Specification {
    private static final String NEW_LINE = "\r\n"
    
    @Unroll
    def "should display '#text'"() {
        given:
            def outStream = new ByteArrayOutputStream()
            System.setOut(new PrintStream(outStream))
            
            def sut =new ToConsoleDisplayAdapter()
        
        when:
            sut.displayText(text)
        
        then:
            outStream.toByteArray() == (text + NEW_LINE).getBytes()
        
        where:
            text << ['test', 'my text', 'It works']
    }
}
