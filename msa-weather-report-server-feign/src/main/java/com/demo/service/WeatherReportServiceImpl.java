package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.vo.Forecast;
import com.demo.vo.Weather;
import com.demo.vo.WeatherResponse;

@Service
public class WeatherReportServiceImpl implements WeatherReportService{
	@Autowired 
	private WeatherDataClient weatherDataClient;
	
	//根据城市Id获取天气预报的数据
	@Override
	public Weather getDataByCityId(String cityId) {
		//由天气数据API微服务来提供根据城市Id查询对应城市的天气的功能
		WeatherResponse resp=weatherDataClient.getDataByCityId(cityId);
		Weather data=resp.getData();
		return data;
	}
}
