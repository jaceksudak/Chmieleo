package pl.jaceksudak.chmieleo.excavator.entity.actions;


import lombok.*;
import pl.jaceksudak.chmieleo.excavator.entity.Dictionary;
import pl.jaceksudak.chmieleo.excavator.entity.Selector;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("ATTRIBUTE")
public class AttributeAction extends AdditionalAction {

    @Builder
    public AttributeAction(Long id, String value, int ordering, Selector selector, Dictionary dictionary) {
        super(id, value, ordering, selector, dictionary, ActionType.ATTRIBUTE);
    }

    @Override
    protected ActionType initActionType() {
        return ActionType.ATTRIBUTE;
    }
}
