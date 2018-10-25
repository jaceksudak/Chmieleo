package pl.jaceksudak.chmieleo.excavator.entity;


import lombok.*;
import pl.jaceksudak.chmieleo.excavator.entity.actions.AdditionalAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(of = {"id", "value"})
@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String value;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dictionary")
    private List<AdditionalAction> additionalActions = new ArrayList<>();


    public void addAdditionalAction(AdditionalAction additionalAction) {
        additionalActions.add(additionalAction);
        additionalAction.setDictionary(this);
    }

    public void removeAdditionalAction(AdditionalAction additionalAction) {
        additionalActions.remove(additionalAction);
        additionalAction.setDictionary(null);
    }

}
