package com.example.feignconsumer.client;

import org.springframework.stereotype.Component;

/**
 * 实现BookServiceClient接口，当网络不通或者访问失败时，返回固定/默认内容
 *
 * @author wangxl
 * @date 2019-09-17
 */
@Component
public class BookServiceClientFallback implements BookServiceClient {
    @Override
    public String getBookById(String id) {
        return "熔断返回值 api调用网络不通";
    }
}
