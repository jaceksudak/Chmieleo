package pl.jaceksudak.chmieleo.excavator.actions;

import org.jsoup.nodes.Element;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

public class TextActionMethod extends ActionMethod {

    @Override
    public Object apply(Object element, AdditionalAction action) {
        return ((Element)element).text();
    }

    @Override
    public ActionType getActionType() {
        return ActionType.TEXT;
    }
}
