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
public class Ticket {
    
    protected int ticketID;
    protected int userID;
    protected Date departureDate;
    protected String departureCity;
    protected String destination;
    protected Flight firstFlight;
    protected Flight secondFlight;
    protected double totalPrice;

    public Ticket(int ticketID, int userID, Date departureDate, String departureCity, String destination, Flight firstFlight, double totalPrice) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.departureDate = departureDate;
        this.departureCity = departureCity;
        this.destination = destination;
        this.firstFlight = firstFlight;
        this.secondFlight = null;
        this.totalPrice = totalPrice;
    }

    public int getTicketID() {
        return ticketID;
    }

    public int getUserID() {
        return userID;
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

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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
    }

    public void setSecondFlight(Flight secondFlight) {
        this.secondFlight = secondFlight;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
