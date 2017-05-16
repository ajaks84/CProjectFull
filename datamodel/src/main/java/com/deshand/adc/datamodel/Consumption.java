package com.deshand.adc.datamodel;

public class Consumption {
	
	private Integer id;

    private Integer reportId;
    
    private Integer materialId;
    
    private Double quantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Consumption [id=" + id + ", reportId=" + reportId + ", materialId=" + materialId + ", quantity=" + quantity
				+ "]";
	}

	
    
    

}
