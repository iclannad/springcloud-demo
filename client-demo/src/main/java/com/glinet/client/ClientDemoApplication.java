package com.glinet.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@RestController
public class ClientDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientDemoApplication.class, args);
    }

    @Resource
    private NameService nameService;

    @GetMapping("/client/hello")
    public String hello() {
        return nameService.helloFromServer();
    }

    @FeignClient("server-demo")
    static interface NameService {
        @RequestMapping("/server/hello")
        public String helloFromServer();
    }

}
