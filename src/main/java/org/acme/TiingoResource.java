package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/stocks")
public class TiingoResource {
    @RestClient
    TiingoService tiingoService;

    @Path("/{ticker}/prices")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String prices(
            @PathParam("ticker") String ticker
            ,@QueryParam("startDate") String startDate // e.g: 2023-6-23 23 jun 2023
            ,@QueryParam("endDate") String endDate
    ) {
        final Price price = tiingoService.getLatestPrice(
                ticker
                ,startDate
                ,endDate
                ).stream()
//                .findFirst() // this would be the closing price of the start date
                .reduce((first, second) -> second) // find last because the closing price of a period should be the end date's one
                .orElse(null)
                ;
        assert price != null;
        return String.format("Latest closing price on %s for [%s] is %s", price.getDate(), ticker, price.getClose());
    }
}
