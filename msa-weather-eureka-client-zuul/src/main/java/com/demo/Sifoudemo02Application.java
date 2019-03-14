package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

//@ServletComponentScan(basePackages="com.demo.web.servlet")
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class Sifoudemo02Application {

	public static void main(String[] args) {
		SpringApplication.run(Sifoudemo02Application.class, args);
	}
}
