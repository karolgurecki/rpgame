package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterAttackPower;
import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.character.dto.CharacterStatus;
import eu.kgorecki.rpgame.character.dto.CharacterTakeDamageCommand;
import eu.kgorecki.rpgame.character.dto.EquipItemCommand;
import eu.kgorecki.rpgame.items.dto.ItemId;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class Character {
    private final Id id;
    private final String name;
    private final String sex;
    private final String skinColor;
    private final String job;
    private final int attackModifier;
    private final int hitPoints;
    private final Set<ItemId> equipment;

    public Character(String name, String sex, String skinColor, String job) {
        this.id = Id.generateId();
        this.name = name;
        this.sex = sex;
        this.skinColor = skinColor;
        this.job = job;
        this.equipment = new HashSet<>();
        this.attackModifier = new Random().nextInt(4);
        this.hitPoints = 10;
    }

    public Character(Id id, String name, String sex, String skinColor, String job, int attackModifier, int hitPoints, Set<ItemId> equipment) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.skinColor = skinColor;
        this.job = job;
        this.attackModifier = attackModifier;
        this.hitPoints = hitPoints;
        this.equipment = equipment;
    }

    static Character createCharacter(UserInteractionPort userInterfaceFacade) {
        String name = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_NAME);
        String sex = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SEX);
        String skinColor = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SKIN_COLOR);
        String job = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_JOB);

        return new Character(name, sex, skinColor, job);
    }

    public CharacterId getIdAsCharacterId() {
        return CharacterId.of(id.getId());
    }

    CharacterAttackPower getCharacterAttackPower(ItemsPort itemsPort) {
        int attackPowerFromItemInEquipment = equipment.stream()
                .map(itemsPort::findAttackPower)
                .filter(Optional::isPresent)
                .mapToInt(Optional::get)
                .sum();

        return new CharacterAttackPower(attackModifier + attackPowerFromItemInEquipment);
    }

    Character takeDamage(CharacterTakeDamageCommand command, ItemsPort itemsPort) {

        int defencePowerFromItemInEquipment = equipment.stream()
                .map(itemsPort::findDefencePower)
                .filter(Optional::isPresent)
                .mapToInt(Optional::get)
                .sum();

        int actualEnemyAttackPower = getActualEnemyAttackPower(defencePowerFromItemInEquipment, command.getAttackPower());

        if (defencePowerFromItemInEquipment > 0 && actualEnemyAttackPower <= 0) {
            return this;
        }

        int newHitPoints = hitPoints - actualEnemyAttackPower;

        return new Character(id, name, sex, skinColor, job, attackModifier, newHitPoints, equipment);
    }

    private int getActualEnemyAttackPower(int defencePowerFromItemInEquipment, int attackPower) {
        return defencePowerFromItemInEquipment > 0 ? defencePowerFromItemInEquipment - attackPower : attackPower;
    }

    CharacterStatus getStatus() {
        return hitPoints > 0 ? CharacterStatus.ALIVE : CharacterStatus.DEAD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Character status:" +
                "\nname = " + name +
                "\nsex = " + sex +
                "\nskinColor = " + skinColor +
                "\njob = " + job +
                "\nbase attack power = " + attackModifier +
                "\nhitPoints = " + hitPoints;
    }

    Character equipItem(EquipItemCommand command, UserInteractionPort userInteractionPort) {
        equipment.add(command.getItemId());

        userInteractionPort.displayText("Item equipped");

        return this;
    }
}
