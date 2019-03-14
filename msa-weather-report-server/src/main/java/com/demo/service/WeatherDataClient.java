package com.demo.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.vo.City;
import com.demo.vo.WeatherResponse;

@FeignClient("msa-weather-data-eureka")
public interface WeatherDataClient {
	//通过城市Id查询对应城市的天气数据
	@RequestMapping(value="/weather/cityId",method=RequestMethod.GET)
	WeatherResponse getDataByCityId(@RequestParam("cityId")String cityId);
}
