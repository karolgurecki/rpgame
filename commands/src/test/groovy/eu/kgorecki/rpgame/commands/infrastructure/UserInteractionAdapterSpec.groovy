package eu.kgorecki.rpgame.commands.infrastructure

import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory
import spock.lang.Specification
import spock.lang.Unroll

class UserInteractionAdapterSpec extends Specification {

    def NEW_LINE = '\r\n'

    @Unroll
    def "should display '#text'"() {
        given:
            def outStream = new ByteArrayOutputStream()
            System.setOut(new PrintStream(outStream))

            def sut = new UserInteractionAdapter(UserInterfaceFacadeFactory.createFacade())

        when:
            sut.displayText(text)

        then:
            outStream.toByteArray() == (text + NEW_LINE).getBytes()

        where:
            text << ['test', 'my text', 'It works']
    }
}
