package pl.jaceksudak.chmieleo.excavator.actions;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

@Slf4j
public class ContainsActionMethod extends ActionMethod {

    @Override
    public ActionType getActionType() {
        return ActionType.CONTAINS;
    }

    @Override
    public Object apply(Object element, AdditionalAction action) {
        String elementText = ((Element)element).text();
        if (elementText.toLowerCase().contains(action.getValue().toLowerCase())) {
            return "true";
        } else {
            return "false";
        }
    }
}
