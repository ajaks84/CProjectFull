package com.deshand.adc.web.model;

public class FactoryModel {
	
	private Integer id;

    private String name;
    
    private Integer country_id;
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountry_id() {
		return country_id;
	}

	public void setCountry_id(Integer country_id) {
		this.country_id = country_id;
	}

	@Override
	public String toString() {
		return "FactoryModel [id=" + id + ", name=" + name + ", country_id=" + country_id + "]";
	}
    
	

}
