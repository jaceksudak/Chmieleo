package pl.jaceksudak.chmieleo.excavator.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import pl.jaceksudak.chmieleo.excavator.entity.Property;
import pl.jaceksudak.chmieleo.excavator.enums.ItemType;
import pl.jaceksudak.chmieleo.excavator.exceptions.ExcavatorPropertiesApplierException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Slf4j
@Stateless
public class ItemExtractor {

    @EJB
    private SelectorApplier selectorApplier;

    public Object handle(ItemType itemType, Document doc, List<Property> properties) {
        Object item = createItemInstance(itemType);
        GenericFieldSetter<?> genericFieldSetter = new GenericFieldSetter<>(item);
        for (Property property : properties) {
            String fieldValue = selectorApplier.selectOne(doc, property.getSelector());
            genericFieldSetter.setField(property.getFieldName(), fieldValue);
        }
        return item;
    }

    private Object createItemInstance(ItemType itemType) {
        try {
            return Class.forName(itemType.getClassName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExcavatorPropertiesApplierException(e);
        }
    }
}
