package eu.kgorecki.rpgame.character

import eu.kgorecki.rpgame.character.domain.*
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus
import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand
import spock.lang.Specification

class CharacterFacadeSpec extends Specification {
    def mockedUserInterfaceFacade = Mock(UserInteractionPort)
    def repositoryAdapter = new SingleCharacterRepositoryAdapterForTests()
    def mockedSavePort = Mock(SavePort)
    def mockedLoadPort = Mock(LoadPort)
    
    def sut = CharacterFacadeFactory.createFacade(repositoryAdapter, mockedUserInterfaceFacade, mockedSavePort, mockedLoadPort)
    
    def "should create character if one not exists"() {
        when:
            def resultStatus = sut.createCharacter()
        
        then:
            resultStatus == CharacterCreationStatus.CREATED
            
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_NAME) >> 'test name'
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SKIN_COLOR) >> 'test skin'
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_JOB) >> 'test job'
            1 * mockedUserInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SEX) >> 'test sex'
            
            with(repositoryAdapter.character) {
                name == 'test name'
                skinColor == 'test skin'
                job == 'test job'
                sex == 'test sex'
            }
    }

    def "should already create status when character already exists"() {
        given:
            repositoryAdapter.character = new Character('', '', '', '')
        
        when:
            def resultStatus = sut.createCharacter()
        
        then:
            resultStatus == CharacterCreationStatus.ALREADY_CREATED
        
        cleanup:
            repositoryAdapter.character = null
    }
    
    def "should save character if it is loaded"() {
        
        given:
            def character = new Character('', '', '', '')
            repositoryAdapter.character = character
        
        when:
            sut.saveCharacters()
        
        then:
            1 * mockedSavePort.save(character)
        
        cleanup:
            repositoryAdapter.character = null
    }
    
    def "should not try to save character if one is not loaded"() {
        given:
            repositoryAdapter.character = null
        
        when:
            sut.saveCharacters()
        
        then:
            0 * mockedSavePort.save(_ as Character)
    }
    
    def "should load character data to repository"() {
        given:
            def dut = new Character('test', 'test', '', '')
        
        when:
            sut.loadCharacter()
        
        then:
            1 * mockedLoadPort.load() >> Optional.of(dut)
            repositoryAdapter.character == dut
    }
    
    def "should not override existing data in character repository when character was not loaded"() {
        given:
            def dut = new Character('test', 'test', '', '')
            repositoryAdapter.character = dut
            mockedLoadPort.load() >> Optional.empty()
        
        when:
            sut.loadCharacter()
        
        then:
            repositoryAdapter.character == dut
    }

    def "should take damage when attacked"() {
        given:
            def dut = new Character('test', 'test', '', '')
            repositoryAdapter.save dut

            def attackPowerOfEnemy = 2
        when:
            sut.takeDamage(new CharacterTakeDamageCommand(dut.getIdAsCharacterId(), attackPowerOfEnemy))

        then:
            repositoryAdapter.character.hitPoints == dut.hitPoints - attackPowerOfEnemy
    }

    class SingleCharacterRepositoryAdapterForTests implements RepositoryPort {

        private Character character
        private CharacterId characterId

        @Override
        void save(Character character) {
            this.character = character
            this.characterId = character.getIdAsCharacterId()
        }

        @Override
        Optional<Character> findById(CharacterId id) {
            if (Objects.nonNull(characterId) && characterId == id) {
                return Optional.of(character)
            }

            Optional.empty()
        }

        @Override
        long count() {
            character == null ? 0 : 1
        }

        @Override
        Optional<Character> findLastCreated() {
            Optional.ofNullable(character)
        }
    }
}