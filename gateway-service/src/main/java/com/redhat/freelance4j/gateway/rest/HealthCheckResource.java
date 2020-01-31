package com.redhat.freelance4j.gateway.rest;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Health
@ApplicationScoped
public class HealthCheckResource implements HealthCheck {

    
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("server-state").up().build();
    }

}
