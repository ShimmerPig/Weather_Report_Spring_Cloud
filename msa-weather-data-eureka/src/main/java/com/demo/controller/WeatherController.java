package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.WeatherDataService;
import com.demo.vo.WeatherResponse;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	@Autowired
	private WeatherDataService weatherDataService;
	
	@GetMapping("/cityId")
	public WeatherResponse getWeatherByCityId(String cityId) {
		return weatherDataService.getDataByCityId(cityId);
	}
	
	@GetMapping("/cityName")
	public WeatherResponse getWeatherByCityName(String cityName) {
		return weatherDataService.getDataByCityName(cityName);
	}
}
