package com.deshand.adc.web.model;

public class MaterialModel {
	
	private Integer id;

    private String name;
    
    private String unit;
    
    private Boolean deleted;

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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Material [id=" + id + ", name=" + name + ", unit=" + unit + ", deleted=" + deleted + "]";
	}
    
    

}
