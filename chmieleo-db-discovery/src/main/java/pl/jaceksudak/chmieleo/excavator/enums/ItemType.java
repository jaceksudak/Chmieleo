package pl.jaceksudak.chmieleo.excavator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ItemType {
    HOP("pl.jaceksudak.chmieleo.storage.entity.Hop"),
    MALT("pl.jaceksudak.chmieleo.storage.entity.Malt");

    private String className;

}
