package org.izba.first_rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	
	private String errormessage;
	private int errorcode;
	private String errorCommnet;
	
	public ErrorMessage() {
		
	}

	public ErrorMessage(String errormessage, int errorcode, String errorCommnet) {
		super();
		this.errormessage = errormessage;
		this.errorcode = errorcode;
		this.errorCommnet = errorCommnet;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrorCommnet() {
		return errorCommnet;
	}

	public void setErrorCommnet(String errorCommnet) {
		this.errorCommnet = errorCommnet;
	}
	
}
