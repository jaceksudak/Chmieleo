package pl.jaceksudak.chmieleo.excavator.services;

import lombok.extern.slf4j.Slf4j;
import pl.jaceksudak.chmieleo.excavator.actions.*;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
@Slf4j
public class ActionApplier {

    private Map<ActionType, ActionMethod> actionMap;

    @PostConstruct
    private void init() {
        actionMap = new HashMap<>();
        addAllActions();
    }

    private void addAllActions() { // TODO: change to reflection mechanism or annotation on ActionMethod class
        actionMap.put(ActionType.EXCLUDE, new ExcludeWordsActionMethod());
        actionMap.put(ActionType.ATTRIBUTE, new AttributeActionMethod());
        actionMap.put(ActionType.CONTAINS, new ContainsActionMethod());
        actionMap.put(ActionType.TEXT, new TextActionMethod());
//        actionMap.put(ActionType.XYZ ...)
//        ...
    }

    public Object apply(AdditionalAction action, Object element) {
        ActionMethod actionMethod = actionMap.get(action.getActionType());
        return actionMethod.apply(element, action);
    }
}
