package com.demo.service;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.vo.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherDataServiceImpl implements WeatherDataService{
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);  
	
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String url=WEATHER_URI+ "citykey=" + cityId;
		return this.doGetWeather(url);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String url = WEATHER_URI + "city=" + cityName;
		return this.doGetWeather(url);
	}
	
	//抽离一个请求天气数据的方法
	private WeatherResponse doGetWeather(String url) {
		//将天气的url作为key，若key存在则证明这个天气保存在缓存中
		String key=url;
		//使用工具类将string类型转换为WeatherResponse类型
		ObjectMapper mapper=new ObjectMapper();
		WeatherResponse resp=null;
		String strBody=null;
		//ops用来从缓存中获取与写入数据
		ValueOperations<String, String>ops=stringRedisTemplate.opsForValue();
		
		//先查看缓存，若缓存中存在该天气的数据，则从缓存中获取
		if(stringRedisTemplate.hasKey(key)) {
			logger.info("Redis has data");
			strBody=ops.get(key);
		}else {
			logger.info("Redis don't has data");
			//原来是缓存没有就去调用API接口
			//现在改成缓存没有直接抛出异常
			throw new RuntimeException("Don't has data!");
		}

		//将返回的数据转换成WeatherResponse的形式
		try {
			resp=mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			//e.printStackTrace();
			logger.error("Error!",e);
		}
		return resp;
	}
}