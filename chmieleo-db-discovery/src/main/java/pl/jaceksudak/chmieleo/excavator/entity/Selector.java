package pl.jaceksudak.chmieleo.excavator.entity;

import lombok.*;
import pl.jaceksudak.chmieleo.excavator.enums.ResultType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "value", "resultType"})
@Entity
public class Selector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String value;

    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    @OneToOne(mappedBy = "selector")
    private ItemList itemList;

    @OneToOne(mappedBy = "selector")
    private Property property;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "selector", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalAction> additionalActions = new ArrayList<>();


    public void addAdditionalAction(AdditionalAction additionalAction) {
        additionalActions.add(additionalAction);
        additionalAction.setSelector(this);
    }

    public void removeAdditionalAction(AdditionalAction additionalAction) {
        additionalActions.remove(additionalAction);
        additionalAction.setSelector(null);
    }

    @Override
    public String toString() {
        return "Selector{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", resultType=" + resultType +
                ", itemList=" + (itemList != null ? itemList.getId() : null) +
                ", property=" + (property != null ? property.getId() : null) +
                ", additionalActions=" + additionalActions +
                '}';
    }
}
