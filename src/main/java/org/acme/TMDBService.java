package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/account/21447725/watchlist/movies")
@RegisterRestClient(configKey = "tmdb")
public interface TMDBService {
    @GET
    @ClientHeaderParam(name = "Authorization", value ="Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYmVmZjUyMDYyODY4OWQ2N2QxZTgzM2Q5MGFkZTYyNCIsIm5iZiI6MTcyOTYwMTMzNC4yODM2ODgsInN1YiI6IjY2YmY1ZGY0NWQyYWYyZGFlMzlhMDhjYyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.cXgM1q19NNnhLttpJU7BKkCFVtbo6o7O1p15fF3BvXk")
    TMDBWatchList getTMDBWatchList(@QueryParam("language") String language);
}
