package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.CityClient;
import com.demo.service.WeatherReportService;
import com.demo.vo.City;
import com.demo.vo.Weather;

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);  
	
	//返回天气信息使用的
	@Autowired
	private WeatherReportService weatherReportService;
	
	@Autowired
	private CityClient cityClient;
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/test01")
	public int test01() {
		List<City>list=null;
		try {
			list=cityClient.listCity();
		}catch (Exception e) {
			logger.error("Exception！",e);
		}
		return list.size();
	}
	
	
	//传入城市Id得到对应城市的天气数据
	@GetMapping("/cityId/{cityId}")
	public Weather getReportByCityId(@PathVariable("cityId")String cityId,Model model)throws Exception{
		System.out.println(")))))))))))))))))))))))))))))))))00");
		//获取城市Id列表
		//由城市数据API微服务来提供数据
		List<City>cityList=null;
		
		try {
			cityList=cityClient.listCity();
		}catch (Exception e) {
			logger.error("Exception！",e);
		}
		
		Weather weather=weatherReportService.getDataByCityId(cityId);
		
		return weather;
		
//		model.addAttribute("title","猪猪天气预报");
//		model.addAttribute("cityId",cityId);
//		//返回listget前端，供用户选择
//		model.addAttribute("cityList",cityList);
//		model.addAttribute("report",weatherReportService.getDataByCityId(cityId));
//		return new ModelAndView("weather/report", "reportModel", model);
	}
	
}
