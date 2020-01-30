package com.redhat.freelance4j.project.api;

import java.util.List;

import com.redhat.freelance4j.project.model.Project;
import com.redhat.freelance4j.project.verticle.service.ProjectService;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.healthchecks.HealthCheckHandler;
import io.vertx.ext.healthchecks.Status;


public class ApiVerticle extends AbstractVerticle {

    private ProjectService projectService;

    public ApiVerticle(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {

        Router router = Router.router(vertx);
        router.get("/projects").handler(this::getProjects);
        router.get("/projects/:projectId").handler(this::getProject);
        router.get("/projects/status/:status").handler(this::getProductByStatus);
        
        router.get("/health/readiness").handler(rc -> rc.response().end("OK"));
        
        HealthCheckHandler healthCheckHandler = HealthCheckHandler.create(vertx).register("health", f -> health(f));
        router.get("/health/liveness").handler(healthCheckHandler);

        

        // Health Checks

        // TODO: Add Health Check code here for
        //       - /health/readiness
        //       - /health/liveness



        // Static content for swagger docs
        router.route().handler(StaticHandler.create());
        
        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(config().getInteger("project.http.port", 8080), result -> {
                if (result.succeeded()) {
                    startFuture.complete();
                } else {
                    startFuture.fail(result.cause());
                }
            });
    }
    
    private void health(Future<Status> future) {
    	projectService.ping(ar -> {
            if (ar.succeeded()) {
                // HealthCheckHandler has a timeout of 1000s. If timeout is exceeded, the future will be failed
                if (!future.isComplete()) {
                    future.complete(Status.OK());
                }
            } else {
                if (!future.isComplete()) {
                    future.complete(Status.KO());
                }
            }
        });
    }
        

    private void getProjects(RoutingContext rc) {
    	projectService.getProjects(ar -> {
            if (ar.succeeded()) {
                List<Project> products = ar.result();
                JsonArray json = new JsonArray();
                products.stream()
                    .map(p -> p.toJson())
                    .forEach(p -> json.add(p));
                rc.response()
                    .putHeader("Content-type", "application/json")
                    .end(json.encodePrettily());
            } else {
                rc.fail(ar.cause());
            }
        });
    }

    private void getProject(RoutingContext rc) {
        String projectId = rc.request().getParam("projectId");
        projectService.getProject(projectId, ar -> {
            if (ar.succeeded()) {
                Project product = ar.result();
                JsonObject json;
                if (product != null) {
                    json = product.toJson();
                    rc.response()
                        .putHeader("Content-type", "application/json")
                        .end(json.encodePrettily());
                } else {
                    rc.fail(404);
                }
            } else {
                rc.fail(ar.cause());
            }
        });
    }
    
    private void getProductByStatus(RoutingContext rc) {
        String status = rc.request().getParam("status");
        projectService.getProjectsByStatus(status, ar -> {
            if (ar.succeeded()) {
            	List<Project> products = ar.result();
                JsonArray json = new JsonArray();
                products.stream()
                    .map(p -> p.toJson())
                    .forEach(p -> json.add(p));
                rc.response()
                    .putHeader("Content-type", "application/json")
                    .end(json.encodePrettily());
            } else {
                rc.fail(ar.cause());
            }
        });
    }



}
