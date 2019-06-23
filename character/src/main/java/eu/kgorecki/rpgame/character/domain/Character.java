package eu.kgorecki.rpgame.character.domain;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

import java.util.HashSet;
import java.util.Objects;
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

    public static Character createCharacter(UserInterfaceFacade userInterfaceFacade) {
        String name = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_NAME);
        String sex = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SEX);
        String skinColor = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_SKIN_COLOR);
        String job = userInterfaceFacade.getUserInputWithTextInTheSameLine(UserMassages.ENTER_JOB);

        return new Character(name, sex, skinColor, job);
    }

    public CharacterId getIdAsCharacterId() {
        return CharacterId.of(id.getId());
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
}