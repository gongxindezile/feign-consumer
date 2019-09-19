package com.example.feignconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 在eureka-provider服务停止后, 可以从 熔断器hystrix 获取返回值
 *
 * 核心代码主要包括两点,
 * 1, 对应接口添加@FeignClient，并完成对应服务提供者的requestMapping映射。
 * 2，在启动类加上@EnableFeignClients(basePackages = {"com.yq.client"})，
 * 我的serviceClieng位于com.yq.client包。
 *
 * @author wangxl
 * @date 2019-09-16
 */
@FeignClient(value = "eureka-provider", fallback = BookServiceClientFallback.class)
public interface BookServiceClient {

    @RequestMapping(value="/book/{id}", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
    String getBookById(@PathVariable(value = "id") String id);

//    @RequestMapping(value="/v1/users/{userId}", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public User getUser(@PathVariable(value = "userId") String userId);
//
//    @RequestMapping(value="/v1/users/queryById", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
//    public User getUserByQueryParam(@RequestParam("userId") String userId);
//
//    @RequestMapping(value="/v1/users", method= RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public String createUser();
}
