package com.deshand.adc.datamodel;

public class Country {
	
	private Integer id;

    private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer authorId) {
		this.id = authorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + "]";
	} 
	
	
    
    

}
