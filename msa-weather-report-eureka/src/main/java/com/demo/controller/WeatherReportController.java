package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.WeatherReportService;
import com.demo.vo.City;

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	private final static Logger logger = LoggerFactory.getLogger(WeatherReportController.class);  
	
	//返回天气信息使用的
	@Autowired
	private WeatherReportService weatherReportService;
	
	@GetMapping("/cityId")
	public ModelAndView getReportByCityId(String cityId,Model model)throws Exception{
		//获取城市Id列表
		//TODO 改为由城市数据API微服务来提供数据
		List<City>cityList=null;
		
		try {
			cityList=new ArrayList<>();
			City city=new City();
			city.setCityId("101280601");
			city.setCityName("深圳");
			cityList.add(city);
		}catch (Exception e) {
			logger.error("Exception！",e);
		}
		
		model.addAttribute("title","猪猪天气预报");
		model.addAttribute("cityId",cityId);
		//返回listget前端，供用户选择
		model.addAttribute("cityList",cityList);
		model.addAttribute("report",weatherReportService.getDataByCityId(cityId));
		return new ModelAndView("weather/report", "reportModel", model);
	}
	
}
