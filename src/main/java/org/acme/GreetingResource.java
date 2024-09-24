package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "quarkus.greeting")
    String greeting;

    @RestClient
    WorldClockService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return greeting + service.getNow("Amsterdam").datetime;
    }
}
