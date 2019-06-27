package eu.kgorecki.rpgame.character.infrastructure;

import eu.kgorecki.rpgame.character.domain.ItemsPort;
import eu.kgorecki.rpgame.items.ItemFacade;
import eu.kgorecki.rpgame.items.dto.DisplayItemInfoCommand;
import eu.kgorecki.rpgame.items.dto.ItemId;
import eu.kgorecki.rpgame.items.dto.ItemStatistics;
import eu.kgorecki.rpgame.items.dto.ItemStatisticsQuery;

import java.util.Optional;

public class ItemsAdapter implements ItemsPort {

    private final ItemFacade itemFacade;

    public ItemsAdapter(ItemFacade itemFacade) {
        this.itemFacade = itemFacade;
    }

    @Override
    public Optional<Integer> findAttackPower(ItemId itemId) {
        return itemFacade.findItemStatistics(ItemStatisticsQuery.of(itemId))
                .map(ItemStatistics::getAttackModifier);
    }

    @Override
    public Optional<Integer> findDefencePower(ItemId itemId) {
        return itemFacade.findItemStatistics(ItemStatisticsQuery.of(itemId))
                .map(ItemStatistics::getDefenceModifier);
    }

    @Override
    public void printInformation(ItemId itemId) {
        itemFacade.displayItemInformation(DisplayItemInfoCommand.of(itemId));
    }
}
