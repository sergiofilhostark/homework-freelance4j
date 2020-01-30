package com.redhat.freelance4j.freelancer.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.redhat.freelance4j.freelancer.model.Freelancer;

@Component
public class FreelancerServiceImpl implements FreelancerService{
	
	@PersistenceContext
	private EntityManager em;
	
	public Freelancer getFreelancer(String freelancerId) {
		return em.find(Freelancer.class, freelancerId);
	}

	
	@SuppressWarnings("unchecked")
	public List<Freelancer> getFreelancers() {
		
		Query query = em.createQuery("select f from Freelancer f order by f.freelancerId ");		
		
		List<Freelancer> resultList = query.getResultList();
		return resultList;
	}

}
