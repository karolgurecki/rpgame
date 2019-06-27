package eu.kgorecki.rpgame.commands.application.commands

import eu.kgorecki.rpgame.commands.application.Command
import eu.kgorecki.rpgame.commands.application.CommandMapFactory
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort
import spock.lang.Specification

class ListCommandCommandSpec extends Specification {
    
    def "should print add available commands"() {
        given: 'build command map'
            def firstCommand = 'test1'
            def secondCommand = 'test2'
            
            CommandMapFactory.commandMap([new TestCommand(firstCommand), new TestCommand(secondCommand)])
        
        and: 'build list command command'
            
            def mockedUserInteractionPort = Mock(UserInteractionPort)
            def sut = new ListCommandCommand(mockedUserInteractionPort)
        
        when:
            sut.execute()
        
        then:
            1 * mockedUserInteractionPort.displayText(firstCommand)
            1 * mockedUserInteractionPort.displayText(secondCommand)
    }
    
    class TestCommand implements Command {
        
        def name
        
        TestCommand(name) {
            this.name = name
        }
        
        @Override
        void execute() {
        
        }
        
        @Override
        String getStringForWithCommandMustByExecuted() {
            return name
        }
    }
}
