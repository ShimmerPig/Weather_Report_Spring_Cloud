package com.demo.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.demo.service.CityClient;
import com.demo.service.WeatherDataCollectionService;
import com.demo.vo.City;

//Eureka的定时任务
@Component
public class WeatherDataSyncTask {
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncTask.class);  

	@Autowired
	private WeatherDataCollectionService weatherDataCollectionService;

	@Autowired
	private CityClient cityClient;
	
	//每隔1800秒执行一次
	@Scheduled(fixedRate=1800000)
	public void weatherDataSync() {
		System.out.println("!!!!!!!!!!!!!!!!!!!!");
		logger.info("Weather Data Sync Job. Start！");
		// 获取城市ID列表
		//由城市数据API微服务来提供数据
		List<City> cityList = null;
		try {
			
			//由城市数据API微服务提供数据
			cityList = cityClient.listCity();
			
		} catch (Exception e) {
			logger.error("Exception!", e);
		}

		// 遍历城市ID获取天气
		for (City city : cityList) {
			String cityId = city.getCityId();
			logger.info("Weather Data Sync Job, cityId:" + cityId);
			//将通过城市Id将其对应的天气数据同步到Redis缓存中
			weatherDataCollectionService.syncDataByCityId(cityId);
		}
		
		logger.info("Weather Data Sync Job. End！");
		
	}
}
