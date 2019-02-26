package com.demo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherDataCollectionServiceImpl implements WeatherDataCollectionService{
	private static final String WEATHER_URL = "http://wthrcdn.etouch.cn/weather_mini?";

	//设置缓存无效的时间
	private static final long TIME_OUT = 1800L; // 1800s
	
	//httpClient的客户端
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	//根据城市的Id同步天气数据
	@Override
	public void syncDataByCityId(String cityId) {
		String url = WEATHER_URL + "citykey=" + cityId;
		this.saveWeatherData(url);
	}
	
	//把天气数据放在缓存中
	private void saveWeatherData(String url) {
		//将rul作为天气数据的key进行保存
		String key=url;
		String strBody=null;
		ValueOperations<String, String>ops=stringRedisTemplate.opsForValue();
		
		//调用服务接口来获取数据
		ResponseEntity<String>respString=restTemplate.getForEntity(url, String.class);
	
		//判断请求状态
		if(respString.getStatusCodeValue()==200) {
			strBody=respString.getBody();
		}
		ops.set(key, strBody,TIME_OUT,TimeUnit.SECONDS);
	}
	
}
