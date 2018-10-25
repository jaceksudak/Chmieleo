package pl.jaceksudak.chmieleo.excavator.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import pl.jaceksudak.chmieleo.excavator.entity.ItemList;
import pl.jaceksudak.chmieleo.excavator.entity.Shop;
import pl.jaceksudak.chmieleo.excavator.repository.ShopRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.print.Doc;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
@Slf4j
public class ShopExcavator {

    @EJB
    private DocumentFetcher fetcher;

    @EJB
    private SelectorApplier selectorApplier;

    @EJB
    private ItemExtractor itemExtractor;

    @EJB
    private ShopRepository shopRepository;


    public void excavate(Long shopId) {
        Shop shop = shopRepository.find(shopId);
        List<ItemList> itemLists = shop.getItemLists();
        itemLists.forEach(this::extractAndSaveItemsFromList);
    }

    private void extractAndSaveItemsFromList(ItemList itemList) {   // TODO: save items to db
        Set<String> itemUrlSet = extractItemUrls(itemList);
        List<Object> items = itemUrlSet.stream().map(url -> {
            Object item = itemExtractor.handle(itemList.getItemType(), fetcher.fetch(url), itemList.getProperties());
            log.info(item.toString());
            return item;
        }).collect(Collectors.toList());
    }

    private Set<String> extractItemUrls(ItemList itemList) {
        Set<String> itemUrlSet = new HashSet<>();
        String listUrl = itemList.getShop().getBaseUri() + itemList.getUri();
        int itemUrlSetSize = itemUrlSet.size();
        for(int i = 1;; i++) {
            Document doc = fetcher.fetchListDocument(listUrl, i);
            Set<String> itemFromListUrls = selectorApplier.selectAll(doc, itemList.getSelector());
            itemUrlSet.addAll(itemFromListUrls);
            if (itemUrlSet.size() == itemUrlSetSize) {
                break;
            }
            itemUrlSetSize = itemUrlSet.size();
        }
        return itemUrlSet;
    }

}
