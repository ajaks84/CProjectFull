package com.deshand.adc.datamodel;

import java.sql.Date;

public class Report {
	
    private Integer id;
    private Date date; //Java.util or java.sql?
    private Integer shift;
    private Integer register;
    private Integer input;
    private Integer output;
    private Integer lineId;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getShift() {
		return shift;
	}
	public void setShift(Integer shift) {
		this.shift = shift;
	}
	
	public Integer getInput() {
		return input;
	}
	public void setInput(Integer input) {
		this.input = input;
	}
	public Integer getOutput() {
		return output;
	}
	public void setOutput(Integer output) {
		this.output = output;
	}
	public Integer getLineId() {
		return lineId;
	}
	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}
	public Integer getRegister() {
		return register;
	}
	public void setRegister(Integer register) {
		this.register = register;
	}
	@Override
	public String toString() {
		return "Report [id=" + id + ", date=" + date + ", shift=" + shift + ", register=" + register + ", input="
				+ input + ", output=" + output + ", lineId=" + lineId + "]";
	}
	
	

    
}
