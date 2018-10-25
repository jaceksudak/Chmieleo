package pl.jaceksudak.chmieleo.excavator.services;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.jaceksudak.chmieleo.excavator.entity.Selector;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.exceptions.ExcavatorSelectorException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Stateless
@Slf4j
public class SelectorApplier {

    private static final String NO_ELEMENT_FOUND_FOR_SELECTOR = "No element found for Selector with id %s";
    private static final String MORE_THAN_ONE_ELEMENT_FOUND_FOR_SELECTOR = "More than one Element found for Selector %s";

    @EJB
    private ActionApplier actionApplier;

    public String selectOne(Document document, Selector selector) {
        Object element = selectOneElement(document, selector);
        return (String)applyAdditionalActions(element, selector.getAdditionalActions());
    }

    public Set<String> selectAll(Document document, Selector selector) {
        Stream<Object> elements = selectAllElements(document, selector);
        return elements.map(e -> (String)applyAdditionalActions(e, selector.getAdditionalActions())).collect(Collectors.toSet());
    }

    private Element selectOneElement(Document document, Selector selector) {
        Elements elements = document.select(selector.getValue());
        if (elements.size() == 0) {
            log.error(String.format(NO_ELEMENT_FOUND_FOR_SELECTOR, selector.getId()));
            throw new ExcavatorSelectorException(String.format(NO_ELEMENT_FOUND_FOR_SELECTOR, selector.getId()));
        }
        if (elements.size() > 1) {
            log.error(String.format(MORE_THAN_ONE_ELEMENT_FOUND_FOR_SELECTOR, selector.getId()));
            throw new ExcavatorSelectorException(String.format(MORE_THAN_ONE_ELEMENT_FOUND_FOR_SELECTOR, selector.getId()));
        }
        return elements.get(0);
    }

    private Stream<Object> selectAllElements(Document document, Selector selector) {
        Elements elements = document.select(selector.getValue());
        return elements.stream().map(e -> (Object)e);
    }

    private Object applyAdditionalActions(Object element, List<AdditionalAction> additionalActions) {
        Collections.sort(additionalActions);
        for (AdditionalAction additionalAction : additionalActions) {
            element = actionApplier.apply(additionalAction, element);
        }
        return element;
    }
}
