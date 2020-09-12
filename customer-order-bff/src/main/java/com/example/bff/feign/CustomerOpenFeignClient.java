package com.example.bff.feign;

import com.example.bff.web.FindCustomerResponse;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "customer-service",
        fallbackFactory = CustomerFeignClientFallbackFactory.class)
public interface CustomerOpenFeignClient {
    @RequestMapping(path = "/customer-order/customer-service/customer/{customerId}", method = RequestMethod.GET)
    FindCustomerResponse findCustomer(@PathVariable(name = "customerId") String customerId) throws Exception ;

}

@Component
class CustomerFeignClientFallbackFactory implements FallbackFactory<CustomerOpenFeignClient> {
    @Override
    public CustomerOpenFeignClient create(Throwable t) {
        return new CustomerOpenFeignClient() {
            private final Logger LOGGER = LoggerFactory.getLogger(CustomerOpenFeignClient.class);

            @Override
            public FindCustomerResponse findCustomer(String customerId) throws Exception {
               String msg = "feignClient를 이용한 사용자의 출금 서비스 호출에 문제가 있습니다.";
               LOGGER.debug(msg, t);
               throw new Exception(msg);
            }

        };
    }
}