package eu.kgorecki.rpgame.character;

import eu.kgorecki.rpgame.character.domain.CreationService;
import eu.kgorecki.rpgame.character.domain.RepositoryPort;
import eu.kgorecki.rpgame.character.infrastructure.SingleCharacterRepositoryAdapter;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

import java.util.Objects;

public class CharacterFacadeFactory {

    private static CharacterFacade instance;

    private CharacterFacadeFactory() {
    }

    public static CharacterFacade createFacade(){
        if(Objects.isNull(instance)){
            instance = createFacade(new SingleCharacterRepositoryAdapter(), UserInterfaceFacadeFactory.createFacade());
        }

        return instance;
    }

    private static CharacterFacade createFacade(RepositoryPort repositoryPort, UserInterfaceFacade userInterfaceFacade) {
        CreationService creationService = new CreationService(repositoryPort, userInterfaceFacade);

        return new CharacterFacade(creationService);
    }
}
