package eu.kgorecki.rpgame.enemy

import eu.kgorecki.rpgame.enemy.domain.Enemy
import eu.kgorecki.rpgame.enemy.domain.Id
import eu.kgorecki.rpgame.enemy.domain.RepositoryPort
import eu.kgorecki.rpgame.enemy.domain.UserInteractionPort
import eu.kgorecki.rpgame.enemy.dto.*
import spock.lang.Specification

class EnemyFacadeSpec extends Specification {
    def repositoryPort = new InMemoryRepositoryAdapterForTests()
    def mockedUserInteractionPort = Mock(UserInteractionPort)

    def sut = EnemyFacadeFactory.createFacade(repositoryPort, mockedUserInteractionPort)

    def "should get enemy id when list of enemies is not empty"() {
        given:
            def enemy = Enemy.of(0, 'test enemy', 1, 8, 8)

            repositoryPort.enemies.clear()
            repositoryPort.enemies.add(enemy)

        when:
            def enemyId = sut.findRandomEnemy()

        then:
            enemyId.filter({ id -> id == EnemyId.of(0) }).isPresent()
    }

    def "should get enemy id from list of enemies"() {
        given:
            def enemies = [Enemy.of(0, 'test enemy', 1, 1, 1),
                           Enemy.of(1, 'test enemy1', 1, 1, 1)]

            repositoryPort.enemies.clear()
            repositoryPort.enemies.addAll(enemies)

        when:
            def enemyId = sut.findRandomEnemy()

        then:
            enemies.any { it -> it.getIdAsEnemyId() == enemyId.get() }
    }

    def "should return ALIVE for enemyStatus when enemy hit points >0 "() {
        given:
            def enemy = Enemy.of(0, 'test enemy', 1, 8, 8)

            repositoryPort.enemies.clear()
            repositoryPort.enemies.add(enemy)

        expect:
            sut.getStatus(new EnemyStatusQuery(EnemyId.of(0))) == EnemyStatus.ALIVE
    }

    def "should return DEAD for enemyStatus when enemy hit points =0 "() {
        given:
            def enemy = Enemy.of(0, 'test enemy', 0, 8, 8)

            repositoryPort.enemies.clear()
            repositoryPort.enemies.add(enemy)

        expect:
            sut.getStatus(new EnemyStatusQuery(EnemyId.of(0))) == EnemyStatus.DEAD
    }

    def "should return NOT_EXISTS for enemyStatus when enemy  not exists"() {
        given:
            repositoryPort.enemies.clear()

        expect:
            sut.getStatus(new EnemyStatusQuery(EnemyId.of(100))) == EnemyStatus.NOT_EXISTS
    }

    def "should try to print enemy information"() {
        given:
            def enemy = Enemy.of(0, 'test enemy', 0, 8, 8)

            repositoryPort.enemies.clear()
            repositoryPort.enemies.add(enemy)

        when:
            sut.printEnemyInformation(new PrintEnemyInformationCommand(EnemyId.of(0)))

        then:
            1 * mockedUserInteractionPort.displayText(_ as String)
    }


    def "should take damage when attacked"() {
        given:
            def enemy = Enemy.of(0, 'test enemy', 20, 8, 8)

            repositoryPort.enemies.clear()
            repositoryPort.enemies.add(enemy)

            def attackPowerOfChracter = 2
        when:
            sut.takeDamage(new EnemyTakeDamageCommand(enemy.getIdAsEnemyId(), attackPowerOfChracter))

        then:
            repositoryPort.findOne(enemy.getId()).get().hitPoints == enemy.hitPoints - attackPowerOfChracter
    }

    class InMemoryRepositoryAdapterForTests implements RepositoryPort {
        private final Set<Enemy> enemies = new HashSet<>()

        @Override
        Optional<Enemy> findOne(Id id) {
            enemies
                    .stream()
                    .filter({ enemy -> (enemy.getId() == id) })
                    .findFirst()
        }

        @Override
        void saveOrUpdate(Enemy enemy) {
            enemies.remove(enemy)
            enemies.add(enemy)
        }

        @Override
        Optional<Enemy> findRandomEnemy() {
            if (enemies.isEmpty()) {
                return Optional.empty()
            }

            int enemyNumber = new Random().nextInt(enemies.size())

            Optional.of((Enemy) enemies.toArray()[enemyNumber])
        }
    }
}
