package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/time/current/zone")
@RegisterRestClient(configKey = "work-clock-service")
public interface WorldClockService {
    @GET
    WorkClock getNow(@QueryParam("timeZone") String timeZone);
}
