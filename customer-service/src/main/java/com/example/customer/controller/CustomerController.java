package com.example.customer.controller;

import com.example.customer.domain.Customer;
import com.example.customer.service.CustomerService;
import com.example.customer.webapi.CreateCustomerRequest;
import com.example.customer.webapi.CreateCustomerResponse;
import com.example.customer.webapi.FindCustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

//  @Autowired
  public CustomerService customerService = new CustomerService();

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
    System.out.println("☆☆☆ NAME: " + createCustomerRequest.getName());
    System.out.println("☆☆☆ CreditLimit: " + createCustomerRequest.getCreditLimit().toString());

    Customer customer = customerService.createCustomer(createCustomerRequest.getName(), createCustomerRequest.getCreditLimit());
    return new CreateCustomerResponse(customer.getId());
  }

  @RequestMapping(path = "/customer/{customerId}", method = RequestMethod.GET)
  public FindCustomerResponse findCustomer(@PathVariable(name = "customerId") String customerId) {
    System.out.println("=====> CustomerId: " + customerId);

    Customer customer = customerService.findCustomer(Long.parseLong(customerId));

    return new FindCustomerResponse( customer.getId(), customer.getName(), customer.getCreditLimit().getAmount().longValue());
  }
}
