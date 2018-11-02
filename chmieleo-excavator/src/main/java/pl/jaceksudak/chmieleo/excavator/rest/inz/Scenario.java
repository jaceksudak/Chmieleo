package pl.jaceksudak.chmieleo.excavator.rest.inz;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Scenario {
    private Long id;
    private List<Point> points;
    private ScenarioPreview scenarioPreview;
}
