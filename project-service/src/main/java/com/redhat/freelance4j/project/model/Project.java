package com.redhat.freelance4j.project.model;

import java.io.Serializable;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject
public class Project implements Serializable {

    private static final long serialVersionUID = -6994655395272795259L;

    private String projectId;
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String description;
    private String status;

    public Project() {

    }

    public Project(String projectId, String firstName, String lastName, String email, String title,
			String description, String status) {
		super();
		this.projectId = projectId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.title = title;
		this.description = description;
		this.status = status;
	}




	public Project(JsonObject json) {
        this.projectId = json.getString("projectId");
        this.firstName = json.getString("firstName");
        this.lastName = json.getString("lastName");
        this.email = json.getString("email");
        this.title = json.getString("title");
        this.description = json.getString("description");
        this.status = json.getString("status");
    }
	
	public JsonObject toJson() {
        final JsonObject json = new JsonObject();
        json.put("projectId", this.projectId);
        json.put("firstName", this.firstName);
        json.put("lastName", this.lastName);
        json.put("email", this.email);
        json.put("title", this.title);
        json.put("description", this.description);
        json.put("status", this.status);
        return json;
    }

 

    public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


    
    public static void main(String[] args) {
    	Project p = new Project("555","Sergio","Santos","ssantos@redhat.com","Title 1","Desc 1", "open");
    	System.out.println(p.toJson());
    	p = new Project("656","Jack","Aka","eee@redhat.com","Title 12","Desc 156", "in_progress");
    	System.out.println(p.toJson());
    	p = new Project("777","Joe","Bastos","rrr@redhat.com","Title 13","Desc 1345", "completed");
    	System.out.println(p.toJson());
    	p = new Project("888","Joao","Rives","ttt@redhat.com","Title 14","Desc 3451", "cancelled");
    	System.out.println(p.toJson());
    	p = new Project("999","Dada","Opa","yyy@redhat.com","Title 15","Desc 15", "open");
    	System.out.println(p.toJson());
	}

}
