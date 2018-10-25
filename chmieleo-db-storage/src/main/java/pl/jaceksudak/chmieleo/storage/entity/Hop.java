package pl.jaceksudak.chmieleo.storage.entity;

import lombok.*;
import pl.jaceksudak.chmieleo.storage.enums.HopForm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@NoArgsConstructor
public class Hop extends BaseItem implements SomeEntity {

    @Column
    private Integer harvestYear;

    @Enumerated(EnumType.STRING)
    private HopForm hopForm;

    @Builder
    public Hop(BigDecimal price, Boolean availability, String title, Integer harvestYear, HopForm hopForm) {
        super(null, price, availability, title);
        this.harvestYear = harvestYear;
        this.hopForm = hopForm;
    }
}
