package pl.jaceksudak.chmieleo.excavator.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id", "fieldName"})
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String fieldName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "itemListId")
    private ItemList itemList;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "selectorId")
    private Selector selector;


    public void addSelector(Selector selector) {
        this.selector = selector;
        selector.setProperty(this);
    }

    public void removeSelector() {
        selector.setProperty(null);
        selector = null;
    }
}
