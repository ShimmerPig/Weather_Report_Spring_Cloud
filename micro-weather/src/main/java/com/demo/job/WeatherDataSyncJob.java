package com.demo.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.demo.service.CityDataService;
import com.demo.service.WeatherDataService;
import com.demo.service.WeatherDataServiceImpl;
import com.demo.vo.City;

//同步天气数据
public class WeatherDataSyncJob extends QuartzJobBean{
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

	@Autowired
	private CityDataService cityDataService;
	
	@Autowired
	private WeatherDataService weatherDataService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("Weather Data Sync Job. Start！");
		//城市ID列表
		List<City>cityList=null;
		
		try {
			//获取xml中的城市ID列表
			cityList=cityDataService.listCity();
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception!", e);
		}
		
		//遍历所有城市ID获取天气
		for(City city:cityList) {
			String cityId=city.getCityId();
			logger.info("Weather Data Sync Job, cityId:" + cityId);
			//实现根据cityid定时同步天气数据到缓存中
			weatherDataService.syncDataByCityId(cityId);
		}
		logger.info("Weather Data Sync Job. End！");
	}  
	
	
}
