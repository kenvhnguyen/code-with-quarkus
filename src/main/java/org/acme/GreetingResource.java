package org.acme;

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

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "quarkus.greeting")
    String greeting;

    @RestClient
    WorldClockService service;

    @Inject
    ErrorService error;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greeting + service.getNow("Amsterdam").datetime;
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
