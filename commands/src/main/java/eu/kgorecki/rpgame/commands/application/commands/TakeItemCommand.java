package eu.kgorecki.rpgame.commands.application.commands;

import eu.kgorecki.rpgame.character.dto.CharacterId;
import eu.kgorecki.rpgame.commands.application.Command;
import eu.kgorecki.rpgame.commands.application.Messages;
import eu.kgorecki.rpgame.commands.application.ports.CharacterPort;
import eu.kgorecki.rpgame.commands.application.ports.UserInteractionPort;
import eu.kgorecki.rpgame.commands.application.ports.WorldPort;
import eu.kgorecki.rpgame.items.dto.ItemId;

import java.util.Optional;
import java.util.function.Consumer;

public class TakeItemCommand implements Command {
    private static final String COMMAND = "take item";
    private final CharacterPort characterPort;
    private final WorldPort worldPort;
    private final UserInteractionPort userInteractionPort;

    public TakeItemCommand(CharacterPort characterPort, WorldPort worldPort, UserInteractionPort userInteractionPort) {
        this.characterPort = characterPort;
        this.worldPort = worldPort;
        this.userInteractionPort = userInteractionPort;
    }

    @Override
    public void execute() {
        worldPort.findItemInCurrentRoom()
                .ifPresentOrElse(equipItem(), () -> userInteractionPort.displayText(Messages.ITEM_NOT_EXISTS));
    }

    private Consumer<ItemId> equipItem() {
        return itemId -> {
            Optional<CharacterId> characterPresentInWorld = worldPort.findCharacterPresentInWorld();

            if (characterPresentInWorld.isEmpty()) {
                userInteractionPort.displayText(Messages.CHARACTER_NOT_EXISTS);
                return;
            }

            shouldEuqipThemIfAlive(itemId, characterPresentInWorld);
        };
    }

    private void shouldEuqipThemIfAlive(ItemId itemId, Optional<CharacterId> characterPresentInWorld) {
        characterPresentInWorld
                .filter(characterPort::isAlive)
                .ifPresentOrElse(characterId -> characterPort.equipItem(characterId, itemId),
                        () -> userInteractionPort.displayText(Messages.CHARACTER_IS_DEAD));
    }

    @Override
    public String getStringForWithCommandMustByExecuted() {
        return COMMAND;
    }
}
