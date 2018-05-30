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
public class Itinerary {
    protected int itineraryId;
    protected Date departureDate;
    protected String departureCity;
    protected String destination;
    protected Flight firstFlight;
    protected Flight secondFlight;
    protected double totalPrice;

    public Itinerary() {
        
    }
    
    public Itinerary(int itineraryId, Date departureDate, String departureCity, String destination, Flight firstFlight) {
        this.itineraryId = itineraryId;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.destination = destination;
        this.firstFlight = firstFlight;
        this.secondFlight = null;
        this.totalPrice = firstFlight.getPrice();
    }

    public int getItineraryId() {
        return itineraryId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getDestination() {
        return destination;
    }

    public Flight getFirstFlight() {
        return firstFlight;
    }

    public Flight getSecondFlight() {
        return secondFlight;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setItineraryId(int itineraryId) {
        this.itineraryId = itineraryId;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFirstFlight(Flight firstFlight) {
        this.firstFlight = firstFlight;
        this.totalPrice = firstFlight.getPrice();
    }

    public void setSecondFlight(Flight secondFlight) {
        this.secondFlight = secondFlight;
        this.totalPrice = (firstFlight.getPrice() + secondFlight.getPrice());
    }
    
}
