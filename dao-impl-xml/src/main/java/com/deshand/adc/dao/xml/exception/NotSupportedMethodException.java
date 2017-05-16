package com.deshand.adc.dao.xml.exception;

public class NotSupportedMethodException extends RuntimeException {

	private static final long serialVersionUID = -7081634636794430182L;

	public NotSupportedMethodException() {
        super("This method is not supported by XML DAO layer");
    }

}
