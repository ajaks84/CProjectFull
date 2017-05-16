package com.deshand.adc.web.model;

public class ProductModel {
	
	private Integer id;

    private String name;
    
    private Integer group;
    

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

	public Integer getGroup() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "FactoryModel [id=" + id + ", name=" + name + ", country_id=" + group + "]";
	}
    
	

}
