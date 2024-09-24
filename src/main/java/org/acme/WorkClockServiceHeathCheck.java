package org.acme;

import io.smallrye.health.checks.UrlHealthCheck;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@ApplicationScoped
public class WorkClockServiceHeathCheck {
    @ConfigProperty(name = "quarkus.rest-client.work-clock-service.url")
    String url;

    @Readiness // define my own readiness probe
    public HealthCheck readiness() { return new UrlHealthCheck(url); }

    @Liveness // define my own Liveness probe
    public HealthCheck liveness() { return new UrlHealthCheck(url); }
}
