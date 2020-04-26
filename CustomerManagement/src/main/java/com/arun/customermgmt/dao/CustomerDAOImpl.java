package com.arun.customermgmt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arun.customermgmt.entity.Customer;

@Repository		// use for component, handle exception translation
public class CustomerDAOImpl implements CustomerDAO {
	
	// need to inject session factory
	@Autowired
	SessionFactory sessionFactory;

	
// transactional annotation has been removed from here bcz we meved this functionality at service layer
//	so service will start transaction and end transaction
	@Override
	public List<Customer> getCustomers() {
		
		// get Hibernate current session
				Session currentSession = sessionFactory.getCurrentSession();
				
				// Create query .... sort by last name
				Query <Customer> query = currentSession.createQuery("from Customer order by lastName" ,
																	Customer.class);
				
				// execute query and get result list  
				List<Customer> customer =query.getResultList();
				
				
		return customer;
	}


	@Override
	public void saveCustomer(Customer customer) {
		// get current hibernate session 
		Session session = sessionFactory.getCurrentSession();
		
		// save the customer
		session.saveOrUpdate(customer); // in background hibernate check is id empty of not is empty then save else update
	}


	@Override
	public Customer getCustomer(int id) {
	
		// get Hibernate current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve / read from database using the primary key
		Customer customer = currentSession.get(Customer.class, id);
		
		return customer; 
	}


	@Override
	public void deleteCustomer(int id) {
		
		// get hibernate current session
		Session session = sessionFactory.getCurrentSession();
		
		// delete object with primery key
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();  // executeUpdate is general perpus method which siply means what ever the query is given do as it is. This work for update delete save..etc
		
	}
	
	

}
