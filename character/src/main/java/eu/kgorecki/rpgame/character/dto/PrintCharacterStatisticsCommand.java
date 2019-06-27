package eu.kgorecki.rpgame.character.dto;

import java.util.Objects;

public class PrintCharacterStatisticsCommand {
    private final CharacterId id;

    public PrintCharacterStatisticsCommand(CharacterId id) {
        this.id = id;
    }

    public CharacterId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrintCharacterStatisticsCommand that = (PrintCharacterStatisticsCommand) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
