package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/daily")
@RegisterRestClient(configKey = "tiingo")
public interface TiingoService {
    @GET
    @Path("/{ticker}/prices")
    @ClientHeaderParam(name = "Authorization", value = "Token 17a826792fb5da23fcb4a631088488bcec616146")
    List<Price> getLatestPrice(
            @PathParam("ticker") String ticker
            ,@QueryParam("startDate") String startDate
            ,@QueryParam("endDate") String endDate
//            ,@QueryParam("token") String token // authenticated with the token either via header or query param
    );
}
