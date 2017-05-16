package com.deshand.adc.datamodel;

public class Product {
	
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

	public Integer getGroupe() {
		return group;
	}

	public void setGroup(Integer group) {
		this.group = group;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", group=" + group + "]";
	}
	
    

}
