package eu.kgorecki.rpgame.character.infrastructure;

import eu.kgorecki.rpgame.character.domain.Character;
import eu.kgorecki.rpgame.character.domain.SavePort;
import eu.kgorecki.rpgame.savestale.SaveStateFacade;

public class SaveAdapter implements SavePort {

    private final SaveStateFacade saveStateFacade;

    public SaveAdapter(SaveStateFacade saveStateFacade) {
        this.saveStateFacade = saveStateFacade;
    }

    @Override
    public void save(Character character) {
        Characters characters = Characters.of(character);

        saveStateFacade.save(characters);
    }
}
