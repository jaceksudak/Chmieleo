package pl.jaceksudak.chmieleo.excavator.entity;

import lombok.*;
import pl.jaceksudak.chmieleo.excavator.enums.ItemType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "itemType", "uri"})
@Entity
public class ItemList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    @Column
    private String uri;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "selectorId")
    private Selector selector;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shopId")
    private Shop shop;

    @Builder.Default
    @OneToMany(mappedBy = "itemList", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Property> properties = new ArrayList<>();


    public void addProperty(Property property) {
        properties.add(property);
        property.setItemList(this);
    }

    public void removeProperty(Property property) {
        properties.remove(property);
        property.setItemList(null);
    }

    public void addSelector(Selector selector) {
        this.selector = selector;
        selector.setItemList(this);
    }

    public void removeSelector() {
        selector.setItemList(null);
        selector = null;
    }

    @Override
    public String toString() {
        return "ItemList{" +
                "id=" + id +
                ", itemType=" + itemType +
                ", uri='" + uri + '\'' +
                ", selector=" + (selector != null ? (selector.getId() + " " + selector.getValue()) : null) +
                ", shop=" + (shop != null ? shop.getName() : null) +
                ", properties=" + (properties == null ? null : properties.stream()
                                    .map(p -> (p.getId() == null ? "" : p.getId().toString()))
                                    .collect(Collectors.joining(", "))) +
                '}';
    }


}
