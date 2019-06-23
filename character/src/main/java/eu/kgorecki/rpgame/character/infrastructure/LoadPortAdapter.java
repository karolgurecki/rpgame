package eu.kgorecki.rpgame.character.infrastructure;

import eu.kgorecki.rpgame.character.domain.Character;
import eu.kgorecki.rpgame.character.domain.LoadPort;
import eu.kgorecki.rpgame.savestale.SaveStateFacade;

import java.util.Optional;

public class LoadPortAdapter implements LoadPort {

    private final SaveStateFacade saveStateFacade;

    public LoadPortAdapter(SaveStateFacade saveStateFacade) {
        this.saveStateFacade = saveStateFacade;
    }

    @Override
    public Optional<Character> load() {
        return saveStateFacade.load(Characters.class)
                .filter(characters -> !characters.getCharacters().isEmpty())
                .map(characters -> characters.getCharacters().get(0));
    }
}
