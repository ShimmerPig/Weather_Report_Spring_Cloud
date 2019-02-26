package com.demo.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.demo.service.WeatherDataCollectionService;
import com.demo.vo.City;

//同步天气数据
public class WeatherDataSyncJob extends QuartzJobBean{
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

	@Autowired
	private WeatherDataCollectionService weatherDataCollectionService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("Weather Data Sync Job. Start！");
		//城市ID列表
		//TODO 改为由城市数据API微服务来提供城市列表的数据
		List<City>cityList=null;
		
		try {
			//TODO 改为由城市数据API微服务来提供城市列表的数据
			//获取xml中的城市ID列表
			//cityList=cityDataService.listCity();
			cityList=new ArrayList<>();
			City city=new City();
			city.setCityId("101280601");
			cityList.add(city);
			
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception!", e);
		}
		
		//遍历所有城市ID获取天气
		for(City city:cityList) {
			String cityId=city.getCityId();
			logger.info("Weather Data Sync Job, cityId:" + cityId);
			//实现根据cityid定时同步天气数据到缓存中
			weatherDataCollectionService.syncDataByCityId(cityId);
		}
		logger.info("Weather Data Sync Job. End！");
	}  
	
	
}
