package eu.kgorecki.rpgame.character


import eu.kgorecki.rpgame.character.domain.Character
import eu.kgorecki.rpgame.character.domain.Id
import eu.kgorecki.rpgame.character.domain.ItemsPort
import eu.kgorecki.rpgame.character.domain.LoadPort
import eu.kgorecki.rpgame.character.domain.RepositoryPort
import eu.kgorecki.rpgame.character.domain.SavePort
import eu.kgorecki.rpgame.character.domain.UserInteractionPort
import eu.kgorecki.rpgame.character.domain.UserMassages
import eu.kgorecki.rpgame.character.dto.CharacterAttackPower
import eu.kgorecki.rpgame.character.dto.CharacterAttackPowerQuery
import eu.kgorecki.rpgame.character.dto.CharacterCreationStatus
import eu.kgorecki.rpgame.character.dto.CharacterId
import eu.kgorecki.rpgame.character.dto.CharacterStatus
import eu.kgorecki.rpgame.character.dto.CharacterStatusQuery
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand
import eu.kgorecki.rpgame.character.dto.EquipItemCommand
import eu.kgorecki.rpgame.items.dto.ItemId
import spock.lang.Specification

class CharacterFacadeSpec extends Specification {
    def mockedUserInterfaceFacade = Mock(UserInteractionPort)
    def repositoryAdapter = new SingleCharacterRepositoryAdapterForTests()
    def mockedSavePort = Mock(SavePort)
    def mockedLoadPort = Mock(LoadPort)
    def mockedItemPort = Mock(ItemsPort)

    def sut = CharacterFacadeFactory.createFacade(repositoryAdapter, mockedUserInterfaceFacade, mockedSavePort, mockedLoadPort, mockedItemPort)

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

    def "should return character status as alive when hit point > 0"() {
        given:
            def dut = new Character(Id.generateId(), 'test', 'test', '', '', 2, 2, Collections.emptySet())

            repositoryAdapter.save dut

        expect:
            sut.findCharacterStatus(new CharacterStatusQuery(dut.getIdAsCharacterId())) == Optional.of(CharacterStatus.ALIVE)
    }

    def "should return character status as dead when hit point > 0"() {
        given:
            def dut = new Character(Id.generateId(), 'test', 'test', '', '', 2, 0, Collections.emptySet())

            repositoryAdapter.save dut
        expect:
            sut.findCharacterStatus(new CharacterStatusQuery(dut.getIdAsCharacterId())) == Optional.of(CharacterStatus.DEAD)
    }

    def "should return character attack power"() {
        given:
            def attackPower = 2
            def dut = new Character(Id.generateId(), 'test', 'test', '', '', attackPower, 0, Collections.emptySet())

            repositoryAdapter.save dut
        expect:
            sut.findAttackPower(new CharacterAttackPowerQuery(dut.getIdAsCharacterId())) == Optional.of(new CharacterAttackPower(attackPower))
    }
    
    
    def "should equip item to  character"() {
        given:
            def dut = new Character('test', 'test', '', '')
            
            repositoryAdapter.save dut
            
            def itemToEquip = ItemId.of(0)
        when:
            sut.equipItem(new EquipItemCommand(dut.getIdAsCharacterId(), itemToEquip))
        
        then:
            repositoryAdapter.character.equipment.contains(itemToEquip)
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