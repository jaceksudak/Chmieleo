package pl.jaceksudak.chmieleo.excavator.rest.inz;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioPreview {
    private Long id;
    private String name;
    private Long estimatedTime;
    private String shortDescription;
    private String longDescription;
}
