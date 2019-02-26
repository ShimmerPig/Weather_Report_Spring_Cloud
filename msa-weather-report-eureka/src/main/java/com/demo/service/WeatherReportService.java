package com.demo.service;

import com.demo.vo.Weather;

public interface WeatherReportService {
	//根据城市ID查询天气信息
	public Weather getDataByCityId(String cityId);
}
