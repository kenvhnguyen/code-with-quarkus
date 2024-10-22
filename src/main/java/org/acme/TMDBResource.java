package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/movies")
public class TMDBResource {

    @RestClient
    TMDBService service;

    @Path("/total")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String total() {
        var watchList = service.getTMDBWatchList("en-US");
        return watchList.total_results.toString();
    }
}
