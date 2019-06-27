package eu.kgorecki.rpgame.character.dto;

import java.util.Objects;

public class AddExperienceCommand {
    private final CharacterId id;
    private final int experiencePoints;

    public AddExperienceCommand(CharacterId id, int attackPower) {
        this.id = id;
        this.experiencePoints = attackPower;
    }

    public CharacterId getId() {
        return id;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddExperienceCommand that = (AddExperienceCommand) o;
        return experiencePoints == that.experiencePoints &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, experiencePoints);
    }
}
