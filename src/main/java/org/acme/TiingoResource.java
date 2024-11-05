package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/stocks")
public class TiingoResource {
    @RestClient
    TiingoService tiingoService;

    @Path("/prices")
    @GET
    public Long prices() {
        return (long) tiingoService.getLatestPrice().size();
    }
}
