package pl.jaceksudak.chmieleo.excavator.actions;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

@Slf4j
public class AttributeActionMethod extends ActionMethod {

    @Override
    public ActionType getActionType() {
        return ActionType.ATTRIBUTE;
    }

    @Override

    public Object apply(Object element, AdditionalAction action) {
        return ((Element) element).attr(action.getValue());
    }

}
