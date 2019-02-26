package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.demo.service.CityDataService;
import com.demo.service.WeatherReportService;

@RestController
@RequestMapping("/report")
public class WeatherReportController {
	//返回天气列表使用的
	@Autowired
	private CityDataService cityDataService;
	
	//返回天气信息使用的
	@Autowired
	private WeatherReportService weatherReportService;
	
	@GetMapping("/cityId")
	public ModelAndView getReportByCityId(String cityId,Model model)throws Exception{
		model.addAttribute("title","猪猪天气预报");
		model.addAttribute("cityId",cityId);
		//返回listget前端，供用户选择
		model.addAttribute("cityList",cityDataService.listCity());
		model.addAttribute("report",weatherReportService.getDataByCityId(cityId));
		return new ModelAndView("weather/report", "reportModel", model);
	}
	
}
