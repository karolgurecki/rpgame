package eu.kgorecki.rpgame.character;

import eu.kgorecki.rpgame.character.domain.CreationService;
import eu.kgorecki.rpgame.character.domain.LoadService;
import eu.kgorecki.rpgame.character.domain.RepositoryPort;
import eu.kgorecki.rpgame.character.domain.SavePort;
import eu.kgorecki.rpgame.character.domain.SaveService;
import eu.kgorecki.rpgame.character.infrastructure.SaveAdapter;
import eu.kgorecki.rpgame.character.infrastructure.SingleCharacterRepositoryAdapter;
import eu.kgorecki.rpgame.savestale.SaveStateFacadeFactory;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

import java.util.Objects;

public class CharacterFacadeFactory {

    private static CharacterFacade instance;

    private CharacterFacadeFactory() {
    }

    public static CharacterFacade createFacade(){
        if(Objects.isNull(instance)){
            instance = createFacade(new SingleCharacterRepositoryAdapter(), UserInterfaceFacadeFactory.createFacade(),
                    new SaveAdapter(SaveStateFacadeFactory.createFacade()));
        }

        return instance;
    }

    private static CharacterFacade createFacade(RepositoryPort repositoryPort, UserInterfaceFacade userInterfaceFacade, SavePort savePort) {
        CreationService creationService = new CreationService(repositoryPort, userInterfaceFacade);
        SaveService saveService = new SaveService(savePort, repositoryPort, userInterfaceFacade);
        LoadService loadService = new LoadService();

        return new CharacterFacade(creationService, saveService, loadService);
    }
}
