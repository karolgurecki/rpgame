package eu.kgorecki.rpgame.items.domain;

import eu.kgorecki.rpgame.items.dto.DisplayItemInfoCommand;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.items.dto.ItemStatistics;
import eu.kgorecki.rpgame.items.dto.ItemStatisticsQuery;
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DomainService {

    private final RepositoryPort repositoryPort;
    private final UserInterfaceFacade userInterfaceFacade;

    public DomainService(RepositoryPort repositoryPort, UserInterfaceFacade userInterfaceFacade) {
        this.repositoryPort = repositoryPort;
        this.userInterfaceFacade = userInterfaceFacade;
    }

    public Optional<ItemId> getRandomItem() {
        int itemCount = repositoryPort.count();

        return itemCount <= 0 ? Optional.empty() : getRandomItem(itemCount);

    }

    private Optional<ItemId> getRandomItem(int itemCount) {
        Random random = ThreadLocalRandom.current();

        return repositoryPort.findOne(Id.of(random.nextInt(itemCount)))
                .map(Item::getIdAsItemId);
    }

    public void displayItemInformation(DisplayItemInfoCommand displayItemInfo) {
        repositoryPort.findOne(Id.of(displayItemInfo.getItemId()))
                .ifPresent(item -> userInterfaceFacade.displayText(item.toString()));
    }

    public Optional<ItemStatistics> findItemStatistics(ItemStatisticsQuery query) {
        return repositoryPort.findOne(Id.of(query.getItemId()))
                .map(Item::getItemStatistics);
    }
}
