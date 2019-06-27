package eu.kgorecki.rpgame.commands.infrastructure;

import eu.kgorecki.rpgame.character.CharacterFacade;
import eu.kgorecki.rpgame.commands.application.LoadPort;
import eu.kgorecki.rpgame.world.WorldFacade;

public class LoadAdapter implements LoadPort {
    private final WorldFacade worldFacade;
    private final CharacterFacade characterFacade;

    public LoadAdapter(WorldFacade worldFacade, CharacterFacade characterFacade) {
        this.worldFacade = worldFacade;
        this.characterFacade = characterFacade;
    }

    @Override
    public void load() {
        characterFacade.loadCharacter();
        worldFacade.loadState();
    }
}
