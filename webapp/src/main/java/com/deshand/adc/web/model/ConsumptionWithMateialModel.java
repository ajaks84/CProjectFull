package com.deshand.adc.web.model;

public class ConsumptionWithMateialModel {

	private Integer id;
	private String name;
	private Double quantity;
	private String unit;
	private Boolean deleted;
	private Integer materialId;
	private Integer reportId;

	@Override
	public String toString() {
		return "ConsumptionWithMateialModel [id=" + id + ", name=" + name + ", quantity=" + quantity + ", unit=" + unit
				+ ", deleted=" + deleted + ", materialId=" + materialId + ", reportlId=" + reportId + "]";
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportlId) {
		this.reportId = reportlId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

}
