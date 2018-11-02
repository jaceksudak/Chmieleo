package pl.jaceksudak.chmieleo.excavator.rest;

import pl.jaceksudak.chmieleo.excavator.rest.inz.Point;
import pl.jaceksudak.chmieleo.excavator.rest.inz.Scenario;
import pl.jaceksudak.chmieleo.excavator.rest.inz.ScenarioPreview;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;

@Path("/test")
public class TestRest {

//        @GET
//        @Produces(MediaType.APPLICATION_JSON)
//        public List<TestClass> getAll() {
//            return Arrays.asList(
//                    TestClass.builder().name("SIEMA").price(14.3).build(),
//                    TestClass.builder().name("MUDZIN").price(3.0).build()
//            );
//        }

        @GET
        @Path("/scenario")
        @Produces(MediaType.APPLICATION_JSON)
        public Scenario getScenario() {
            return Scenario.builder()
                    .id(123213L)
                    .points( Arrays.asList(
                            Point.builder().latitude(13.3)
                                    .longitude(15.0)
                                    .name("dom twojej starej")
                                    .build(),
                            Point.builder().latitude(14.12)
                                    .longitude(76.0)
                                    .name("fabryka siły")
                                    .build()))
                    .scenarioPreview(ScenarioPreview.builder()
                            .estimatedTime(120L)
                            .name("Dzień Sebiksa")
                            .shortDescription("dzień jak codzień")
                            .longDescription("wciel się w rolę Sebiksa i prześledź jego typowy dzień pracy")
                            .build())
                    .build();
        }

}
