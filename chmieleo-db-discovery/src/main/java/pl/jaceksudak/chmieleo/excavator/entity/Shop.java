package pl.jaceksudak.chmieleo.excavator.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id", "name", "baseUri"})
@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String baseUri;

    @Builder.Default
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemList> itemLists = new ArrayList<>();


    public void addItemList(ItemList itemList) {
        itemLists.add(itemList);
        itemList.setShop(this);
    }

    public void removeItemList(ItemList itemList) {
        itemLists.remove(itemList);
        itemList.setShop(null);
    }

}
