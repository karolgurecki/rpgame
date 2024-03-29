package eu.kgorecki.rpgame.commands

import eu.kgorecki.rpgame.commands.application.Command
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort
import eu.kgorecki.rpgame.commands.dto.ExecuteCommandCommand
import spock.lang.Specification

class CommandFacadeSpec extends Specification {
    
    def "should execute correct command"() {
        given:
            def commandToBeExecuted = new CommandToBeExecutedInTest()
    
            def sut = CommandFacadeFactory.createFacade(Map.of(commandToBeExecuted.getStringForWithCommandMustByExecuted().toUpperCase(), commandToBeExecuted), null)
    
        when:
            sut.execute(new ExecuteCommandCommand('Test CommanD'))
        
        then:
            commandToBeExecuted.executed
    }
    
    def "should display information to user that can not find given command"() {
        given:
            def mockedUserInteractionPort = Mock(UserInteractionPort)
            def sut = CommandFacadeFactory.createFacade(Map.of(), mockedUserInteractionPort)
        
        when:
            sut.execute(new ExecuteCommandCommand('Test CommanD'))
        
        then:
            1 * mockedUserInteractionPort.displayText(_ as String)
    }
    
    class CommandToBeExecutedInTest implements Command {
        
        def executed = false
        
        @Override
        void execute() {
            executed = true
        }
        
        @Override
        String getStringForWithCommandMustByExecuted() {
            'test command'
        }
    }
}
