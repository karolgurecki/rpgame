package eu.kgorecki.rpgame.character.infrastructure;

import eu.kgorecki.rpgame.character.domain.Character;

import java.io.Serializable;
import java.util.List;

class Characters implements Serializable {
    private final List<Character> characters;

    public Characters(List<Character> characters) {
        this.characters = characters;
    }

    static Characters of(Character character) {
        return new Characters(List.of(character));
    }

    public List<Character> getCharacters() {
        return characters;
    }


}
