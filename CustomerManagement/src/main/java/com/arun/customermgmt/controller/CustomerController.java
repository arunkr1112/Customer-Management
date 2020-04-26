package com.arun.customermgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.arun.customermgmt.dao.CustomerDAO;
import com.arun.customermgmt.entity.Customer;
import com.arun.customermgmt.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the Customer dao
	/*@Autowired 
	 * private CustomerDAO customerDAO;
	 * i removed this bcz i am not going to use DAO directaly. 
	 * i am going to use new layer called service layer
	 */
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model model) {
		
		// get customers from the dao
		List<Customer> customers = customerService.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers", customers);
		
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		// create model attribute to bind data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		// save the customer using our service
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormUpdate(@RequestParam("customerId") int id, Model model) {
		// get customer from service
		Customer customer = customerService.getCustomer(id);
		// set customer as a model attribute to pre-populate the form
		model.addAttribute("customer", customer);
		
		// send over to our form
		
	return "customer-form";
	} 
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int id) {
		
		// delete the customer
		customerService.deleteCustomer(id);
		
	return "redirect:/customer/list";
	}
}
