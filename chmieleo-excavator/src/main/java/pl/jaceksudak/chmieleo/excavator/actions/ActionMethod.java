package pl.jaceksudak.chmieleo.excavator.actions;

import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ActionMethod {

    static final String NO_VALUE_LEFT_AFTER_ACTION = "No value left after applying action";

    public abstract Object apply(Object element, AdditionalAction action);

    public abstract ActionType getActionType();

    Set<String> getWordsFromValue(AdditionalAction action) {
        String value;
        if (action.getValue() != null) {
            value = action.getValue();
        } else {
            value = action.getDictionary().getValue();
        }
        return  Arrays.stream(value.split(";"))
                .collect(Collectors.toSet());
    }
}
