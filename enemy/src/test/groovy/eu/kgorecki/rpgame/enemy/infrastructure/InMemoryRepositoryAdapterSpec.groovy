package eu.kgorecki.rpgame.enemy.infrastructure

import eu.kgorecki.rpgame.enemy.domain.Enemy
import eu.kgorecki.rpgame.enemy.domain.Id
import spock.lang.Specification

class InMemoryRepositoryAdapterSpec extends Specification {

    def "should return correct enemy if exists for given Id"() {
        given:

            def sut = new InMemoryRepositoryAdapter(Enemy.of(0, "troll", 3, 3, 3))

            def enemyId = new Id(0)
        when:
            def result = sut.findOne(enemyId)

        then:
            result.filter({ enemy -> enemy.id == enemyId }).isPresent()
    }

    def "should return empty optional if enemy do not exists for given Id"() {
        given:
            def sut = new InMemoryRepositoryAdapter(new HashSet<Enemy>())

            def enemyId = new Id(0)
        when:
            def result = sut.findOne(enemyId)

        then:
            result.isEmpty()
    }

    def "should save enemy"() {
        given:
            def sut = new InMemoryRepositoryAdapter(new HashSet<>())
            def dut = Enemy.of(0, "troll", 3, 3, 3)

        when:
            def result = sut.saveOrUpdate(dut)

        then:
            sut.enemies.any { enemy -> enemy == dut }
    }

    def "should override enemy if one with give Id exists"() {
        given:
            def sut = new InMemoryRepositoryAdapter(Enemy.of(0, "troll", 3, 3, 66))
            def dut = Enemy.of(0, "troll", 3, 3, 3)

        when:
            def result = sut.saveOrUpdate(dut)

        then:
            sut.enemies.any { enemy -> enemy == dut }
    }
}
