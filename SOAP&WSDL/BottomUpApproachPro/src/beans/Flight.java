/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author shuhnel
 */
public class Flight {
    
    protected int flightID;
    protected Date departureDate;
    protected String departureCity;
    protected Date arrivalDate;
    protected String destination;
    protected int availableSeats;
    protected double price;
    
    
    public Flight(int flightID, Date departureDate, String departureCity, Date arrivalDate, String destination, int availableSeats, double price) {
        this.flightID = flightID;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.arrivalDate = arrivalDate;
        this.destination = destination;
        this.availableSeats = availableSeats;
        this.price = price;
    }
    
    public Flight() {
        
    }
    
    public int getFlightID() {
        return flightID;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
