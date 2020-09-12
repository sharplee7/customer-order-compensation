package com.example.bff.controller;

import com.example.bff.feign.CustomerOpenFeignClient;
import com.example.bff.feign.OrderOpenFeignClient;
import com.example.bff.web.CustomerOrderState;
import com.example.bff.web.FindCustomerResponse;
import com.example.bff.web.GetOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;


@EnableFeignClients
@RestController
public class BffController {
    @Autowired
    CustomerOpenFeignClient customerOpenFeignClient;

    @Autowired
    OrderOpenFeignClient orderOpenFeignClient;

    @RequestMapping(path = "/{customerId}/{orderId}", method = RequestMethod.GET)
    public CustomerOrderState findCustomer(@PathVariable(name="customerId") String customerId,
                                             @PathVariable(name="orderId") String orderId) throws Exception {

        CustomerOrderState customerOrderState =new CustomerOrderState();
        FindCustomerResponse findCustomerResponse =null;
        findCustomerResponse = customerOpenFeignClient.findCustomer(customerId);

        GetOrderResponse getOrderResponse = null;
        getOrderResponse = orderOpenFeignClient.getOrder(orderId);

        customerOrderState.setCustomerId(findCustomerResponse.getId());
        customerOrderState.setName(findCustomerResponse.getName());
        customerOrderState.setCreditLimit(findCustomerResponse.getCreditLimit());
        customerOrderState.setOrderId(getOrderResponse.getOrderId());
        customerOrderState.setOrderState(getOrderResponse.getOrderState());


        System.out.println(getOrderResponse.getOrderState());



        return customerOrderState;
    }
}
