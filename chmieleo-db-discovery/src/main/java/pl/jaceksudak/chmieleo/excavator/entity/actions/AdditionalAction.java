package pl.jaceksudak.chmieleo.excavator.entity.actions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.jaceksudak.chmieleo.excavator.entity.Dictionary;
import pl.jaceksudak.chmieleo.excavator.entity.Selector;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "value", "actionType", "ordering"})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "actionType")
public abstract class AdditionalAction implements Comparable<AdditionalAction> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    protected Long id;

    @Column
    protected String value;

    @Column
    protected Integer ordering;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "selectorId")
    protected Selector selector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dictionaryId")
    protected Dictionary dictionary;

    @Transient
    protected ActionType actionType;

    public AdditionalAction() {
        this.actionType = initActionType();
    }

    protected abstract ActionType initActionType();

    @Override
    public String toString() {
        return "AdditionalAction{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", actionType=" + actionType +
                ", ordering=" + ordering +
                ", selector=" + (selector != null ? selector.getId() : null) +
                ", dictionary=" + (dictionary != null ? dictionary.getId() : null) +
                '}';
    }

    @Override
    public int compareTo(AdditionalAction o) {
        if (this.getOrdering() < o.getOrdering()) {
            return -1;
        } else if (this.getOrdering().equals(o.getOrdering())) {
            return 0;
        } else {
            return 1;
        }
    }
}
