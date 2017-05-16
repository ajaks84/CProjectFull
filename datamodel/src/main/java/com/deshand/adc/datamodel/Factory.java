package com.deshand.adc.datamodel;

public class Factory {
	
	private Integer id;

    private String name;
    
    private Integer countryId;
    

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

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "Factory [id=" + id + ", name=" + name + ", countryId=" + countryId + "]";
	}
	
	
    
    

}
