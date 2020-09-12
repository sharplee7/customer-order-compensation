package com.example.bff.feign;


import com.example.bff.web.FindCustomerResponse;
import com.example.bff.web.GetOrderResponse;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "order-service",
        fallbackFactory = OrderFeignClientFallbackFactory.class)
public interface OrderOpenFeignClient {
//    @RequestMapping(path = "/customer-order/order-service/customer/{customerId}", method = RequestMethod.GET)
//    FindCustomerResponse findCustomer(@PathVariable(name = "customerId") String customerId) throws Exception ;
    @RequestMapping(value="/customer-order/order-service/orders/{orderId}", method= RequestMethod.GET)
    GetOrderResponse getOrder(@PathVariable(name="orderId") String orderId) throws Exception;
}

@Component
class OrderFeignClientFallbackFactory implements FallbackFactory<OrderOpenFeignClient> {
    @Override
    public OrderOpenFeignClient create(Throwable t) {
        return new OrderOpenFeignClient() {
            @Override
            public GetOrderResponse getOrder(String orderId) throws Exception {
                String msg = "feignClient를 이용한 주문 서비스 호출에 문제가 있습니다.";
                LOGGER.debug(msg, t);
                throw new Exception(msg);
            }

            private final Logger LOGGER = LoggerFactory.getLogger(OrderOpenFeignClient.class);

        };
    }
}