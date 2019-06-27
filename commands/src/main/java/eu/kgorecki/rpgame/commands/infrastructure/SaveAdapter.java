package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.character.CharacterFacade;
import eu.kgorecki.rpgame.commands.application.ports.SavePort;
import eu.kgorecki.rpgame.world.WorldFacade;

public class SaveAdapter implements SavePort {
    private final WorldFacade worldFacade;
    private final CharacterFacade characterFacade;

    public SaveAdapter(WorldFacade worldFacade, CharacterFacade characterFacade) {
        this.worldFacade = worldFacade;
        this.characterFacade = characterFacade;
    }

    @Override
    public void save() {
        characterFacade.saveCharacters();
        worldFacade.saveState();
    }
}
