package com.demo.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.vo.City;

//客户端访问城市数据微服务，从而得到城市list数据
@FeignClient("msa-weather-city-eureka")
public interface CityClient {
	@RequestMapping(value="/cities",method=RequestMethod.GET)
	List<City> listCity()throws Exception;
}
