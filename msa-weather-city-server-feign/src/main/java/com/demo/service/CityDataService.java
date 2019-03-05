package com.demo.service;

import java.util.List;

import com.demo.vo.City;

public interface CityDataService {
	//获取xml中的City列表
	List<City>listCity()throws Exception;
}
