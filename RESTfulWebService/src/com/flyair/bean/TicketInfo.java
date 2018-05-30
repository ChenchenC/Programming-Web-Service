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
@XmlRootElement(name="TicketInfo") 
public class TicketInfo {
	private String flightNumber = "";
	private String departure = "";
	private String destination = "";
	private String date = "";
	private double price = 0.0;
	private int number = 0;
	
	@XmlElement(name="flightNumber")
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	@XmlElement(name="departure")
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	@XmlElement(name="destination")
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@XmlElement(name="date")
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@XmlElement(name="price")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@XmlElement(name="number")
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	

}
