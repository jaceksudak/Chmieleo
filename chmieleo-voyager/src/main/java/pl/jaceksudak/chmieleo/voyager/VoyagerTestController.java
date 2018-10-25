package pl.jaceksudak.chmieleo.voyager;

import lombok.extern.slf4j.Slf4j;
import pl.jaceksudak.chmieleo.excavator.entity.ItemList;
import pl.jaceksudak.chmieleo.excavator.entity.Property;
import pl.jaceksudak.chmieleo.excavator.entity.Selector;
import pl.jaceksudak.chmieleo.excavator.entity.Shop;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.entity.actions.TextAction;
import pl.jaceksudak.chmieleo.excavator.enums.ItemType;
import pl.jaceksudak.chmieleo.excavator.repository.ShopRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@Slf4j
public class VoyagerTestController {

    @EJB
    private ShopRepository shopRepository;

    @PostConstruct
    private void init() {
        log.info("SIEMANKO");

//        test();

//        Hop hop = Hop.builder()
//                .harvestYear(2015)
//                .valid(Boolean.TRUE)
//                .build();
//
//        hopRepository.addNew(hop);

//        log.info(String.format("Hop added: %s", hop.toString()));
    }


    private void test() {
        log.info("zaczynamy");

        Shop shop = Shop.builder()
                .baseUri("twojbrowar.pl")
                .name("TwojBrowar")
                .build();

        ItemList itemList = ItemList.builder()
                .itemType(ItemType.HOP)
                .uri("saeraew?asdAS/asd")
                .build();
        shop.addItemList(itemList);

        Property property = Property.builder()
                .fieldName("price")
                .build();

        Selector selector = Selector.builder()
                .value("a.asdas?asd")
                .build();

        Selector selector2 = Selector.builder()
                .value("a.asdas?asd")
                .build();

        itemList.addProperty(property);
        itemList.addSelector(selector);

        property.addSelector(selector2);

        AdditionalAction additionalAction = TextAction.builder()
                .build();

        selector2.addAdditionalAction(additionalAction);
        selector2.removeAdditionalAction(additionalAction);


        log.info("{}", shop.toString());
        log.info("{}", itemList.toString());
        log.info("{}", property.toString());
        log.info("{}", selector.toString());
        log.info("{}", selector2.toString());
        log.info("{}", additionalAction.toString());

        shopRepository.save(shop);
        log.info("persist");

        Shop foundShop = shopRepository.find(3L);
        log.info("{}", foundShop.toString());
        for (Property property1 : foundShop.getItemLists().get(0).getProperties()) {
            log.info("{}", property1.toString());
            log.info("{}", property1.getSelector().toString());
            log.info("_______________");
        }
        log.info("PAPA");
//
//        shop.removeItemList(itemList);
//        log.info("REMOVED " + shop.toString());


    }
}
