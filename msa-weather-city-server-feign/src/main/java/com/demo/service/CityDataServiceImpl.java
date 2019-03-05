package com.demo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.demo.util.XmlBuilder;
import com.demo.vo.City;
import com.demo.vo.CityList;

@Service
public class CityDataServiceImpl implements CityDataService{

	@Override
	public List<City> listCity() throws Exception {
		Resource resource=new ClassPathResource("citylist.xml");
		BufferedReader br=new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
		StringBuffer buffer=new StringBuffer();
		String line="";
		
		while((line=br.readLine())!=null) {
			buffer.append(line);
		}
		
		br.close();
		
		CityList cityList=(CityList)XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());
		
		return cityList.getCityList();
	}

}
