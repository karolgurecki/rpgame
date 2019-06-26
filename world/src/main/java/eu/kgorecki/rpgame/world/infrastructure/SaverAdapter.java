package eu.kgorecki.rpgame.world.infrastructure;

import eu.kgorecki.rpgame.savestale.SaveStateFacade;
import eu.kgorecki.rpgame.world.domain.SaverPort;
import eu.kgorecki.rpgame.world.domain.World;

public class SaverAdapter implements SaverPort {

    private final SaveStateFacade saveStateFacade;

    public SaverAdapter(SaveStateFacade saveStateFacade) {
        this.saveStateFacade = saveStateFacade;
    }

    @Override
    public void save(World world) {
        saveStateFacade.save(world);
    }
}
