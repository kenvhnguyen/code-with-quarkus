package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/timezone/Europe/")
@RegisterRestClient(configKey = "work-clock-service")
public interface WorldClockService {
    @Path("{city}")
    @GET
    WorkClock getNow(@PathParam("city") String city);
}
