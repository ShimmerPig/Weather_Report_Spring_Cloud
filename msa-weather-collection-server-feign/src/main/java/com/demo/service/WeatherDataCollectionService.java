package com.demo.service;

public interface WeatherDataCollectionService {
	//根据城市ID同步天气数据
	void syncDataByCityId(String cityId);
}
