package com.redhat.freelance4j.freelancer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redhat.freelance4j.freelancer.model.Freelancer;
import com.redhat.freelance4j.freelancer.service.FreelancerService;

@RestController
@RequestMapping("/freelancers")
public class FreelancerEndpoint {
	
	@Autowired
	private FreelancerService freelancerService;
	
	@GetMapping("/{freelancerId}")
	public Freelancer getFreelancer(@PathVariable String freelancerId) {

		return freelancerService.getFreelancer(freelancerId);
	}	
	
	@GetMapping
	public List<Freelancer> getFreelancers() {

		return freelancerService.getFreelancers();
	}	

}
