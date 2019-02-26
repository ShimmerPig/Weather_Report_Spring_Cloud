package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.vo.Weather;
import com.demo.vo.WeatherResponse;

@Service
public class WeatherReportServiceImpl implements WeatherReportService{
	@Autowired
	private WeatherDataService weatherDataService;
	
	@Override
	public Weather getDataByCityId(String cityId) {
		WeatherResponse resp=weatherDataService.getDataByCityId(cityId);
		return resp.getData();
	}

}
