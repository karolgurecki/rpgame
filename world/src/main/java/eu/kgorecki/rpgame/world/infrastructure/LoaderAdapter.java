package eu.kgorecki.rpgame.world.infrastructure;

import eu.kgorecki.rpgame.savestale.SaveStateFacade;
import eu.kgorecki.rpgame.world.domain.LoaderPort;
import eu.kgorecki.rpgame.world.domain.World;

import java.util.Optional;

public class LoaderAdapter implements LoaderPort {

    private final SaveStateFacade saveStateFacade;

    public LoaderAdapter(SaveStateFacade saveStateFacade) {
        this.saveStateFacade = saveStateFacade;
    }

    @Override
    public Optional<World> load() {
        return saveStateFacade.load(World.class);
    }
}
