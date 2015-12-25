package com.ekiras.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ekiras.domain.Person;

@Repository
@Transactional
public class PersonDAO {

	@Autowired 
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> list(Integer offset, Integer maxResults){
		
		//?maxResults:15 是每頁呈現的資料筆數 ; <tag:paginate max="15"
		return sessionFactory.openSession()
				.createCriteria(Person.class)
				.setFirstResult(offset!=null?offset:0)
				.setMaxResults(maxResults!=null?maxResults:15)
				.list();
	}
	
	
	public Long count(){
		return (Long)sessionFactory.openSession()
				.createCriteria(Person.class)
				.setProjection(Projections.rowCount())
				.uniqueResult();
	}
	
	
	public void save(){
		for(int itr=1;itr <= 300 ; itr++){
			Person person = new Person("Person_"+itr,Math.max(25, (itr%2)*35));
			sessionFactory.openSession()
			.save(person);
		}
		
		
		
	}
	
}
