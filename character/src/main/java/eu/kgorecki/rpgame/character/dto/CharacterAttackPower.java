package eu.kgorecki.rpgame.character.dto;

import java.util.Objects;

public class CharacterAttackPower {
    private final int attackPower;

    public CharacterAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterAttackPower that = (CharacterAttackPower) o;
        return attackPower == that.attackPower;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackPower);
    }
}
