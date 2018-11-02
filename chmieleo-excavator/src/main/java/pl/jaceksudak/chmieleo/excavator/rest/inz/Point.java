package pl.jaceksudak.chmieleo.excavator.rest.inz;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Point {
    private Long id;
    private String name;
    private Double longitude;
    private Double latitude;
}
