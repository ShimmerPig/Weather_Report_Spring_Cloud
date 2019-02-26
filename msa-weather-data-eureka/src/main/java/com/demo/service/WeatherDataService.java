package com.demo.service;

import com.demo.vo.WeatherResponse;

public interface WeatherDataService {
	//根据城市ID查询天气数据
	WeatherResponse getDataByCityId(String cityId);
	//根据城市名称查询天气数据
	WeatherResponse getDataByCityName(String cityName);
}
