package com.gf.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCircuitBreaker
@SpringBootApplication
public class GateWayMain
{
    public static void main(String[] args) {
        SpringApplication.run(GateWayMain.class, args);
    }
}
