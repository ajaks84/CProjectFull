package com.deshand.adc.datamodel;

public class ConsumptionWithMaterial {
	
	private Consumption cons;
	private Material mat;
	public Consumption getCons() {
		return cons;
	}
	public void setCons(Consumption cons) {
		this.cons = cons;
	}
	public Material getMat() {
		return mat;
	}
	public void setMat(Material mat) {
		this.mat = mat;
	}
	@Override
	public String toString() {
		return "ConsumptionWithMaterial [cons=" + cons + ", mat=" + mat + "]";
	}
	
	
	
	//Previous version of code
	
//	private Boolean deleted;
//
//    private String unit;
//    
//    private String name;
//    
//    private Double quantity;
//
//	public Boolean getDeleted() {
//		return deleted;
//	}
//
//	public void setDeleted(Boolean deleted) {
//		this.deleted = deleted;
//	}
//
//	public String getUnit() {
//		return unit;
//	}
//
//	public void setUnit(String unit) {
//		this.unit = unit;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Double getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(Double quantity) {
//		this.quantity = quantity;
//	}
//
//	@Override
//	public String toString() {
//		return "ConsumptionWithMaterial [deleted=" + deleted + ", unit=" + unit + ", name=" + name + ", quantity="
//				+ quantity + "]";
//	}
    
    

}
