package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/stocks")
public class TiingoResource {
    @RestClient
    TiingoService tiingoService;

    @Path("/prices")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String prices() {
        final Price price = tiingoService.getLatestPrice().get(0);
        return String.format("Latest price on %s is %s", price.getDate(), price.getClose());
    }
}
