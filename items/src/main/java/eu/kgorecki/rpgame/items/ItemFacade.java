package eu.kgorecki.rpgame.items;

import eu.kgorecki.rpgame.items.domain.DomainService;
import eu.kgorecki.rpgame.items.dto.DisplayItemInfoCommand;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.items.dto.ItemStatistics;
import eu.kgorecki.rpgame.items.dto.ItemStatisticsQuery;

import java.util.Optional;

public class ItemFacade {

    private final DomainService domainService;

    ItemFacade(DomainService domainService) {
        this.domainService = domainService;
    }

    public Optional<ItemId> getRandomItem() {
        return domainService.getRandomItem();
    }

    public void displayItemInformation(DisplayItemInfoCommand displayItemInfo) {
        domainService.displayItemInformation(displayItemInfo);
    }

    public Optional<ItemStatistics> findItemStatistics(ItemStatisticsQuery query) {
        return domainService.findItemStatistics(query);
    }
}
