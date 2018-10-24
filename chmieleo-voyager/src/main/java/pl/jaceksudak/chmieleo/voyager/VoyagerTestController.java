package pl.jaceksudak.chmieleo.voyager;

import lombok.extern.slf4j.Slf4j;
import pl.jaceksudak.chmieleo.excavator.entity.*;
import pl.jaceksudak.chmieleo.excavator.enums.ItemType;
import pl.jaceksudak.chmieleo.excavator.enums.ResultType;
import pl.jaceksudak.chmieleo.excavator.repository.TestRepository;
import pl.jaceksudak.chmieleo.storage.entity.Hop;
import pl.jaceksudak.chmieleo.storage.repository.HopRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@Slf4j
public class VoyagerTestController {

//    @EJB
//    private HopRepository hopRepository;

    @EJB
    private TestRepository testRepository;

    @PostConstruct
    private void init() {
        log.info("SIEMANKO");

        test();

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
                .resultType(ResultType.DECIMAL)
                .value("a.asdas?asd")
                .build();

        Selector selector2 = Selector.builder()
                .resultType(ResultType.STRING)
                .value("a.asdas?asd")
                .build();

        itemList.addProperty(property);
        itemList.addSelector(selector);

        property.addSelector(selector2);

        AdditionalAction additionalAction = AdditionalAction.builder()
                .build();

        selector2.addAdditionalAction(additionalAction);
        selector2.removeAdditionalAction(additionalAction);


        log.info("{}", shop.toString());
        log.info("{}", itemList.toString());
        log.info("{}", property.toString());
        log.info("{}", selector.toString());
        log.info("{}", selector2.toString());
        log.info("{}", additionalAction.toString());

        testRepository.save(shop);
        log.info("persist");
//
//        shop.removeItemList(itemList);
//        log.info("REMOVED " + shop.toString());


    }
}
