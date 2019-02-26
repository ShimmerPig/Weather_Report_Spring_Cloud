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
	
	//缓存的失效时间
	private static final long TIME_OUT=1800L;
	
	//要获得url中的数据，需要引入httpClient的客户端
	@Autowired
	private RestTemplate restTemplate;
	
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
			//缓存没有，再调用服务器接口来获取
			//根据传入的url，通过客户端的get方法发起请求，返回一个string类型
			ResponseEntity<String>respString=restTemplate.getForEntity(url, String.class);
			
			//判断请求状态
			if(respString.getStatusCodeValue()==200) {
				strBody=respString.getBody();
			}
			
			//数据写入缓存
			ops.set(key, strBody,TIME_OUT,TimeUnit.SECONDS);
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

	@Override
	public void syncDataByCityId(String cityId) {
		String url=WEATHER_URI+"citykey=" + cityId;
		this.saveWeatherData(url);
	}
	
	//将天气数据保存到缓存中，不管缓存中是否存在数据
	private void saveWeatherData(String url) {
		//将url作为天气的key进行保存
		String key=url;
		String strBody=null;
		ValueOperations<String, String>ops=stringRedisTemplate.opsForValue();
		
		//通过客户端的get方法发起请求
		ResponseEntity<String>respString=restTemplate.getForEntity(url, String.class);
		
		//判断请求状态
		if(respString.getStatusCodeValue()==200) {
			strBody=respString.getBody();
		}
		
		ops.set(key, strBody,TIME_OUT,TimeUnit.SECONDS);
	}
}
