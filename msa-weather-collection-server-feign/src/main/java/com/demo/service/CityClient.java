package com.demo.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.vo.City;

//创建一个Feign客户端 获取城市数据微服务中的城市列表信息
@FeignClient("msa-weather-city-eureka")
public interface CityClient {
	@RequestMapping(value="/cities",method=RequestMethod.GET)
	List<City> listCity()throws Exception;
}
