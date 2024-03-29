package eu.kgorecki.rpgame.character;

import eu.kgorecki.rpgame.character.domain.ActionService;
import eu.kgorecki.rpgame.character.domain.CreationService;
import eu.kgorecki.rpgame.character.domain.InformationService;
import eu.kgorecki.rpgame.character.domain.ItemsPort;
import eu.kgorecki.rpgame.character.domain.LoadPort;
import eu.kgorecki.rpgame.character.domain.LoadService;
import eu.kgorecki.rpgame.character.domain.RepositoryPort;
import eu.kgorecki.rpgame.character.domain.SavePort;
import eu.kgorecki.rpgame.character.domain.SaveService;
import eu.kgorecki.rpgame.character.domain.UserInteractionPort;
import eu.kgorecki.rpgame.character.infrastructure.ItemsAdapter;
import eu.kgorecki.rpgame.character.infrastructure.LoadPortAdapter;
import eu.kgorecki.rpgame.character.infrastructure.SaveAdapter;
import eu.kgorecki.rpgame.character.infrastructure.SingleCharacterRepositoryAdapter;
import eu.kgorecki.rpgame.character.infrastructure.UserInteractionAdapter;
import eu.kgorecki.rpgame.items.ItemFacadeFactory;
import eu.kgorecki.rpgame.savestale.SaveStateFacadeFactory;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacadeFactory;

import java.util.Objects;

public class CharacterFacadeFactory {

    private static CharacterFacade instance;

    private CharacterFacadeFactory() {
    }

    public static CharacterFacade createFacade() {
        if (Objects.isNull(instance)) {
            SingleCharacterRepositoryAdapter repositoryPort = new SingleCharacterRepositoryAdapter();
            UserInteractionAdapter displayInformationPort = new UserInteractionAdapter(
                    UserInterfaceFacadeFactory.createFacade());
            SaveAdapter savePort = new SaveAdapter(SaveStateFacadeFactory.createFacade());
            LoadPortAdapter loadPort = new LoadPortAdapter(SaveStateFacadeFactory.createFacade());
            ItemsAdapter itemsPort = new ItemsAdapter(ItemFacadeFactory.createFacade());

            instance = createFacade(repositoryPort, displayInformationPort, savePort, loadPort, itemsPort);
        }

        return instance;
    }

    private static CharacterFacade createFacade(RepositoryPort repositoryPort, UserInteractionPort displayInformationPort, SavePort savePort,
                                                LoadPort loadPort, ItemsPort itemsPort) {
        CreationService creationService = new CreationService(repositoryPort, displayInformationPort);
        SaveService saveService = new SaveService(savePort, repositoryPort, displayInformationPort);
        LoadService loadService = new LoadService(loadPort, repositoryPort, displayInformationPort);
        ActionService actionService = new ActionService(repositoryPort, itemsPort, displayInformationPort);
        InformationService informationService = new InformationService(repositoryPort, itemsPort);

        return new CharacterFacade(creationService, saveService, loadService, actionService, informationService);
    }
}
