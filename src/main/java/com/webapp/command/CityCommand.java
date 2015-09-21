package com.webapp.command;

import com.webapp.model.City;
import com.webapp.model.Country;

//웹브라우저에서 올라오는 파라미터를 담기위한 객체 
//입력받은것을 가지고 DB에 입력할 수 있는 모델 City.java를 만들어 낸다.
public class CityCommand {
	
	String name;
	String countryCode;
	String district;
	int population;
	
	public City getCity(){
		City city = new City();
		city.setName(name);
		//ContryCode와 country의 이름을 맞추기 위한 작업
		Country c = new Country();
		c.setCode(countryCode);
		
		city.setCountry(c);
		city.setDistrict(district);
		city.setPopulation(population);
		return city;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	
	
}
