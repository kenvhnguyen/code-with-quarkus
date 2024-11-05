package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/daily/aapl/prices")
@RegisterRestClient(configKey = "tiingo")
public interface TiingoService {
    @GET
    @ClientHeaderParam(name = "Authorization", value ="Token 17a826792fb5da23fcb4a631088488bcec616146")
    List<Price> getLatestPrice();
}
