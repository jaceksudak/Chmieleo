package pl.jaceksudak.chmieleo.excavator.actions;

import lombok.extern.slf4j.Slf4j;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;
import pl.jaceksudak.chmieleo.excavator.exceptions.ExcavatorActionException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Slf4j
public class ExcludeWordsActionMethod extends ActionMethod {

    private static final String MORE_THAN_ONE_WORD_LEFT_AFTER_ACTION = "More than one word left after action";

    @Override
    public ActionType getActionType() {
        return ActionType.EXCLUDE;
    }

    @Override
    public Object apply(Object element, AdditionalAction action) {
        Set<String> excludedWords = getWordsFromValue(action);
        List<String> wordsList = Arrays.asList(((String)element).split(" "));
        wordsList.removeIf(excludedWords::contains);
        if (wordsList.isEmpty()) {
            throw new ExcavatorActionException(NO_VALUE_LEFT_AFTER_ACTION);
        }
        if (wordsList.size() > 1) {
            log.warn(MORE_THAN_ONE_WORD_LEFT_AFTER_ACTION);
        }
        return String.join(" ", wordsList);
    }
}
