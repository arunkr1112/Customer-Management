package com.arun.customermgmt.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.arun.customermgmt.entity.Customer;
@Component
public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer customer);

	public Customer getCustomer(int id);

	public void deleteCustomer(int id);   
}
