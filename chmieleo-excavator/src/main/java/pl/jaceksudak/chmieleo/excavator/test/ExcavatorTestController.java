package pl.jaceksudak.chmieleo.excavator.test;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import pl.jaceksudak.chmieleo.excavator.entity.Property;
import pl.jaceksudak.chmieleo.excavator.entity.Selector;
import pl.jaceksudak.chmieleo.excavator.entity.Shop;
import pl.jaceksudak.chmieleo.excavator.enums.ItemType;
import pl.jaceksudak.chmieleo.excavator.services.ItemExtractor;
import pl.jaceksudak.chmieleo.excavator.services.SelectorApplier;
import pl.jaceksudak.chmieleo.excavator.services.ShopExcavator;
import pl.jaceksudak.chmieleo.storage.repository.HopRepository;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
@Slf4j
public class ExcavatorTestController {

    @EJB
    private HopRepository hopRepository;

    @EJB
    private ShopExcavator shopExcavator;

    @EJB
    private SelectorApplier SelectorApplier;

    @EJB
    private ItemExtractor itemExtractor;

    @PostConstruct
    private void init() {
        shopExcavator.excavate(3L);

//        ItemType itemType = ItemType.HOP;
//        List<Property> properties = new ArrayList<>();
//        properties.add(Property.builder()
//                .fieldName("harvestYear")
//                .selector(Selector.builder().value("2015").build())
//                .build());
//        properties.add(Property.builder()
//                .fieldName("availability")
//                .selector(Selector.builder().value("true").build())
//                .build());
//        properties.add(Property.builder()
//                .fieldName("price")
//                .selector(Selector.builder().value("123.54").build())
//                .build());
//        properties.add(Property.builder()
//                .fieldName("hopForm")
//                .selector(Selector.builder().value("PELLET").build())
//                .build());
//        properties.add(Property.builder()
//                .fieldName("title")
//                .selector(Selector.builder().value("siema mudzin").build())
//                .build());

//        Object object = itemExtractor.selectOne(itemType, properties);
//        log.info("MKOIJ {}", object);
//        try {
//            object = Class.forName(itemType.getClassName()).newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        GenericFieldSetter<?> genericApplier = new GenericFieldSetter<>(object);
//        for (Property property : properties) {
//            String fieldValue = SelectorApplier.selectOne(property.getSelector());
//            genericApplier.setField(property.getFieldName(), fieldValue);
//        }
//
//
//        log.info("SIEMKA: {}", object.toString());


//
//        log.info("SIEMANKO");
////        shopExcavator.dig(3L);
////        secondTestService.prepare();
////        someTest();
//
////        GenericFieldSetter<Hop> genericApplier = new ApplierForHops(hop);
//        genericApplier.setField("harvestYear", "2015");
//        log.info(object.toString());
//        genericApplier.setField("availability", "true");
//        log.info(object.toString());
//        genericApplier.setField("price", "123.54");       // chyba propertie bedzie musialo miec value type
//        log.info(object.toString());
//        genericApplier.setField("hopForm", "PELLET");
//        log.info(object.toString());
//        genericApplier.setField("title", "siema mudzin");
//        log.info(object.toString());
//        Hop hop = Hop.builder()
//                .availability(Boolean.TRUE)
//                .harvestYear(2015)
//                .price(BigDecimal.valueOf(14.23))
//                .title("Jakis tam hopek")
//                .build();
//
//        hopRepository.addNew(hop);
//
//        log.info(String.format("Hop added: %s", hop.toString()));
    }



    public void prepare() throws ClassNotFoundException {
//        List<String> urls = shopExcavator.dig(3L);
//        String firstUrl = urls.get(0);
//        Document doc = fetcher.fetch(firstUrl);
//        Shop shop = shopRepository.find(3L);
//        doShit(doc, shop);
    }

    private void doShit(Document doc, Shop shop) throws ClassNotFoundException {
        String className = shop.getItemLists().get(0).getItemType().getClassName();
        Object object = Class.forName(className);
        List<Property> properties = shop.getItemLists().get(0).getProperties();
//        properties.forEach(s -> log.info(s.toString()));
        Property testProperty = properties.get(0);
        //__________________________________________
//        applySelector(doc, testProperty);
    }



}
