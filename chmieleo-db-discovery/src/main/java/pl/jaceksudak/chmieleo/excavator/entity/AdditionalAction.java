package pl.jaceksudak.chmieleo.excavator.entity;

import lombok.*;
import pl.jaceksudak.chmieleo.excavator.enums.ActionType;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "value", "actionType", "ordering"})
@Entity
public class AdditionalAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String value;

    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @Column
    private int ordering;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "selectorId")
    private Selector selector;

    @Override
    public String toString() {
        return "AdditionalAction{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", actionType=" + actionType +
                ", ordering=" + ordering +
                ", selector=" + (selector != null ? selector.getId() : null) +
                '}';
    }
}
