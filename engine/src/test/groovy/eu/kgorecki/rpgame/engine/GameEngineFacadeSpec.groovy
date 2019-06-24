package eu.kgorecki.rpgame.engine

import eu.kgorecki.rpgame.engine.application.CommandPort
import eu.kgorecki.rpgame.engine.application.EnemyActionPort
import spock.lang.Specification

class GameEngineFacadeSpec extends Specification {
    
    def "game loop should contain read and execution of user commands as well as enemy turn"() {
        given: 'create mock of ports'
            def mockedCommandPort = Mock(CommandPort)
            def mockedEnemyActionPort = Mock(EnemyActionPort)
        
        and: 'create tested facade instance'
            def sut = GameEngineFacadeFactory.createFacade(mockedCommandPort, mockedEnemyActionPort)
        
        and: 'prepare test command'
            def command = 'anyCommand'
        
        when:
            sut.runGame()
        
        then:
            1 * mockedCommandPort.read() >> command
            1 * mockedCommandPort.execute(command)
            1 * mockedEnemyActionPort.executeEnemyTurn() >> { sut.service.runTheGame = false }
    }
}
