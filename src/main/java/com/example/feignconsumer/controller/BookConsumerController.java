package com.example.feignconsumer.controller;

import com.example.feignconsumer.client.BookServiceClient;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可以将feign项目,抽取为 service-feign-api
 * 我们只需定义 client接口  和 fallBack类, 供其他多个微服务模块来调用(只需要 autowired注入即可).
 *
 * @author wangxl
 * @date 2019-09-17
 */
@RestController
@RequestMapping("/book")
public class BookConsumerController {

    @Autowired
    private BookServiceClient bookServiceClient;

    @GetMapping("/{id}")
    public String getBookById(@PathVariable("id") String id) {
        String result = bookServiceClient.getBookById(id);
        System.out.println("feign-consumer调用eureka-provider接口(如果provider服务停止,则是从hystrix熔断中获取接口默认返回值),返回值为:"+result);
        return result;
    }
}
