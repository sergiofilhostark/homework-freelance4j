package com.redhat.freelance4j.gateway.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

@Path("/gateway")
@ApplicationScoped
public class GatewayEndpoint {
	
	
	private WebTarget projectsService;
	
	private WebTarget projectService;
	
	private WebTarget projectStatusService;
	
	private WebTarget freelancerService;
	
	private WebTarget freelancersService;

    
	
    private String projectHost = "http://project-service-ssantos-freelance4j-project.apps.na311.openshift.opentlc.com";
    
	
    private String freelancerHost = "http://freelancer-service-ssantos-freelance4j-freelancer.apps.na311.openshift.opentlc.com";
    
    	
	
	@GET
    @Path("/projects")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProjects() {
		Response response = projectsService.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	return response.readEntity(String.class);
        } else if (response.getStatus() == 404) {
        	return "Not Found";
        } else {
            throw new ServiceUnavailableException();
        }
    }
	
	@GET
    @Path("/projects/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProject(@PathParam("projectId") String projectId) {
		Response response = projectService.resolveTemplate("projectId", projectId).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	return response.readEntity(String.class);
        } else if (response.getStatus() == 404) {
            return "Not Found";      	
        } else {
            throw new ServiceUnavailableException();
        }
    }
	
	@GET
    @Path("/projects/status/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getProjectByStatus(@PathParam("status") String status) {
		Response response = projectStatusService.resolveTemplate("status", status).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	return response.readEntity(String.class);
        } else if (response.getStatus() == 404) {
            return "Not Found";      	
        } else {
            throw new ServiceUnavailableException();
        }
    }
	
	
	@GET
    @Path("/freelancers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFreelancers() {
		Response response = freelancersService.request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	return response.readEntity(String.class);
        } else if (response.getStatus() == 404) {
        	return "Not Found";
        } else {
            throw new ServiceUnavailableException();
        }
    }
	
	@GET
    @Path("/freelancers/{freelancerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFreelancer(@PathParam("freelancerId") String freelancerId) {
		Response response = freelancerService.resolveTemplate("freelancerId", freelancerId).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	return response.readEntity(String.class);
        } else if (response.getStatus() == 404) {
            return "Not Found";      	
        } else {
            throw new ServiceUnavailableException();
        }
    }
	
	@PostConstruct
    public void init() {
		projectsService = ((ResteasyClientBuilder)ClientBuilder.newBuilder()).connectionPoolSize(10).build().target(projectHost)
				.path("projects");
		
		projectService = ((ResteasyClientBuilder)ClientBuilder.newBuilder()).connectionPoolSize(10).build().target(projectHost)
				.path("projects").path("{projectId}");
		
		projectStatusService = ((ResteasyClientBuilder)ClientBuilder.newBuilder()).connectionPoolSize(10).build().target(projectHost)
				.path("projects").path("status").path("{status}");
		
		freelancersService = ((ResteasyClientBuilder)ClientBuilder.newBuilder()).connectionPoolSize(10).build().target(freelancerHost)
				.path("freelancers");
		
		freelancerService = ((ResteasyClientBuilder)ClientBuilder.newBuilder()).connectionPoolSize(10).build().target(freelancerHost)
				.path("freelancers").path("{freelancerId}");
    }

}
