package eu.kgorecki.rpgame.character.dto;

import java.util.Objects;

public class CharacterTakeDamageCommand {
    private final CharacterId id;
    private final int attackPower;

    public CharacterTakeDamageCommand(CharacterId id, int attackPower) {
        this.id = id;
        this.attackPower = attackPower;
    }

    public CharacterId getId() {
        return id;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterTakeDamageCommand that = (CharacterTakeDamageCommand) o;
        return attackPower == that.attackPower &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attackPower);
    }
}
