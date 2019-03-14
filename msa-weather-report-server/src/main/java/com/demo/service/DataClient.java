package com.demo.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.vo.City;
import com.demo.vo.WeatherResponse;

@FeignClient("msa-weather-eureka-client-zuul")
public interface DataClient {

	//获取城市列表
	@RequestMapping(value="/city/cities",method=RequestMethod.GET)
	List<City> listCity()throws Exception;
	
	//通过城市Id查询对应城市的天气数据
	@RequestMapping(value="/data/weather/cityId",method=RequestMethod.GET)
	WeatherResponse getDataByCityId(@RequestParam("cityId")String cityId);
}
