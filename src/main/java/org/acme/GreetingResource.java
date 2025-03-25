package org.acme;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.TimeZone;

@Path("/hello")
public class GreetingResource {

    private final MeterRegistry registry;

    @ConfigProperty(name = "quarkus.greeting")
    String greeting;

    @RestClient
    WorldClockService service;

    @Inject
    ErrorService error;

    public GreetingResource(MeterRegistry registry) {
        this.registry = registry;
        registry.gauge("offsetFromUTC", this, GreetingResource::offSetFromUTC);
    }

    int offSetFromUTC() {
        return TimeZone.getDefault().getOffset(Calendar.ZONE_OFFSET);
    }

    @Counted(value = "time.now")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        var worldClock = service.getNow("Europe/Amsterdam");
        return greeting + worldClock.dateTime + " in " + worldClock.timeZone;
    }

    @Path("/error")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Retry(
            maxRetries = 4,
            delay = 1000,
            delayUnit = ChronoUnit.MILLIS
    )
    @Fallback(FallBackService.class)
    @CircuitBreaker( // to control retry storm
            requestVolumeThreshold = 3,
            failureRatio = 0.8,
            delay = 5000
    )
    public String helloError() {
        return error.msg();
    }
}
