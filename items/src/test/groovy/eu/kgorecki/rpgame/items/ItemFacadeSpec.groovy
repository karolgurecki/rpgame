package eu.kgorecki.rpgame.items

import eu.kgorecki.rpgame.items.domain.Item
import eu.kgorecki.rpgame.items.dto.DisplayItemInfoCommand
import eu.kgorecki.rpgame.items.dto.ItemId
import eu.kgorecki.rpgame.items.dto.ItemStatisticsQuery
import eu.kgorecki.rpgame.items.infrastructure.InMemoryRepositoryAdapter
import eu.kgorecki.rpgame.userinterface.UserInterfaceFacade
import spock.lang.Specification

class ItemFacadeSpec extends Specification {
    
    def repositoryAdapter = new InMemoryRepositoryAdapter(new ArrayList<Item>())
    def mockedUserInterfaceFacade = Mock(UserInterfaceFacade)
    
    def sut = ItemFacadeFactory.createFacade(mockedUserInterfaceFacade, repositoryAdapter)
    
    def "should get item id when list of items is not empty"() {
        given:
            def item = Item.shieldOf(0, 'test shield', BigDecimal.ONE)
            
            repositoryAdapter.items.clear()
            repositoryAdapter.items.add(item)
        
        when:
            def itemId = sut.getRandomItem()
        
        then:
            itemId.filter({ id -> id == item.getIdAsItemId() }).isPresent()
    }
    
    def "should get item  id from list of items"() {
        given:
            def items = [Item.shieldOf(0, 'test shield', BigDecimal.ONE),
                         Item.shieldOf(1, 'test shield1', BigDecimal.ONE)]
            
            repositoryAdapter.items.clear()
            repositoryAdapter.items.addAll(items)
        
        when:
            def itemId = sut.getRandomItem()
        
        then:
            items.any { it -> it.getIdAsItemId() == itemId.get() }
    }
    
    def "should return empty optional when cannot get anny items"() {
        given:
            repositoryAdapter.items.clear()
        
        expect:
            sut.getRandomItem().isEmpty()
    }
    
    def "should display item information when item with given id exists"() {
        given:
            def item = Item.shieldOf(0, 'test shield', BigDecimal.ONE)
    
            repositoryAdapter.items.clear()
            repositoryAdapter.items.add(item)
    
        when:
            sut.displayItemInformation(DisplayItemInfoCommand.of(ItemId.of(0)))
    
        then:
            1 * mockedUserInterfaceFacade.displayText(_ as String)
    }
    
    def "should not display item information when item with given id  not exists"() {
        given:
            repositoryAdapter.items.clear()
    
        when:
            sut.displayItemInformation(DisplayItemInfoCommand.of(ItemId.of(100)))
    
        then:
            0 * mockedUserInterfaceFacade.displayText(_ as String)
    }
    
    def "should get item statistics for shoield type item"() {
        given:
            def item = Item.shieldOf(0, 'test shield', BigDecimal.ONE)
    
            repositoryAdapter.items.clear()
            repositoryAdapter.items.add(item)
    
    
        when:
            def result = sut.findItemStatistics(ItemStatisticsQuery.of(ItemId.of(0)))
    
        then:
            result.filter({ itemStats -> itemStats == item.getItemStatistics() }).isPresent()
    }
}
