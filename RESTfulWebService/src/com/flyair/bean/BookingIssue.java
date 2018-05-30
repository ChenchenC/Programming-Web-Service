/**
 * 
 */
package com.flyair.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chenchen Cheng
 *
 */
@XmlRootElement(name="BookingIssue") 

public class BookingIssue {
	private String bookingId = "";
	private String userId = "";
	private String flightNumber = "";
	private String creditCardNumber = "";
	
	@XmlElement(name="bookingId")
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	@XmlElement(name="userId")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@XmlElement(name="flightNumber")
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	@XmlElement(name="creditCardNumber")
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
}
