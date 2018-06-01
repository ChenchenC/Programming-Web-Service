/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author shuhnel
 */
public class FlightsInfo {
    
    public static ArrayList<Flight> flights;
    
    public static void initData() {
        
        if(flights == null || flights.isEmpty()) {
        
            flights = new ArrayList<>();
            
            Flight flight1 = new Flight(100, new Date(), "Stockholm", new Date(), "London", 320, 400.50);
            Flight flight2 = new Flight(102, new Date(), "London", new Date(), "Singapore", 320, 890.90);
            Flight flight3 = new Flight(103, new Date(), "London", new Date(), "Paris", 0, 300.40);
            Flight flight4 = new Flight(104, new Date(), "London", new Date(), "China", 320, 900.20);
            Flight flight5 = new Flight(105, new Date(), "Stockholm", new Date(), "Amsterdam", 320, 150.50);
            Flight flight6 = new Flight(106, new Date(), "Stockholm", new Date(), "Norway", 320, 130.70);
            Flight flight7 = new Flight(107, new Date(), "Singapore", new Date(), "Thailand", 100, 170.60);
            Flight flight8 = new Flight(108, new Date(), "Singapore", new Date(), "Indonesia", 200, 100.70);
            Flight flight9 = new Flight(109, new Date(), "London", new Date(), "Paris", 100, 220.50);
            Flight flight10 = new Flight(110, new Date(), "Amsterdam", new Date(), "Paris", 200, 180.70);

            flights.add(flight1);
            flights.add(flight2);
            flights.add(flight3);
            flights.add(flight4);
            flights.add(flight5);
            flights.add(flight6);
            flights.add(flight7);
            flights.add(flight8);
            flights.add(flight9);
            flights.add(flight10);
            
        }
    }
    
    public static ArrayList<Flight> getAllFlights() {
        initData();
        return flights;
    }
    
    public static ArrayList<Itinerary> findDirectFlight(String departureCity, String destination, Date departureDate) {
        initData();
        ArrayList<Itinerary> searchResults = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();   
        
        for(Flight flight : flights) {
            String strFlightDepartDate = df.format(flight.getDepartureDate());
            String strDepartDate = df.format(departureDate);
            
            if(strFlightDepartDate.equals(strDepartDate)) {
                if(flight.getDepartureCity().equalsIgnoreCase(departureCity) && flight.getDestination().equalsIgnoreCase(destination)) {
                    Itinerary itinerary = new Itinerary(searchResults.size()+1, departureDate, departureCity, destination, flight);
                    searchResults.add(itinerary);
                }
            }
        }
        
        if(searchResults.isEmpty())
            return null;
        else
            return searchResults;
    }
    
    public static ArrayList<Itinerary> findFlights(Date departureDate, String departureCity, String destination) {
        initData();
        ArrayList<Itinerary> searchResults = new ArrayList<>();
        
        //First find direct flights
        searchResults = findDirectFlight(departureCity, destination, departureDate);
        
        if(searchResults != null)
            return searchResults;
        
        //If there are no direct flights, first find flights departing from the same city
        ArrayList<Itinerary> firstFlights = findDepartingFlights(departureDate, departureCity, destination);
        
        //If dont have first flights means there is no possible connections
        if(firstFlights == null)
            return new ArrayList<>();
        
        //then find the connecting flights from the first flight to the desintaion
        ArrayList<Itinerary> secondFlights = findSecondFlights(departureDate, departureCity, destination, firstFlights);    
        
        ArrayList<Itinerary> flightResults = new ArrayList<>();
        
        //remove flights that don't have second flights
        for(Itinerary itin : secondFlights) {
            if(itin.getSecondFlight() != null) {
                flightResults.add(itin);
            } 
        }
        
        if(flightResults == null)
            return new ArrayList<Itinerary> ();
        else 
            return flightResults;
       
    }
    
    public static ArrayList<Itinerary> findSecondFlights(Date departureDate, String departureCity, String destination, ArrayList<Itinerary> firstFlights) {
        initData();
        int counter = 0;
        ArrayList<Itinerary> results = new ArrayList<>();
        
        try {
            for(Itinerary itin : firstFlights) {
                Flight firstFlight = itin.getFirstFlight();
                String firstFlightDestination = firstFlight.getDestination();
                for(Flight flight : flights) {
                    
                    if(firstFlightDestination.equalsIgnoreCase(flight.getDepartureCity()) && flight.getDestination().equalsIgnoreCase(destination)) {
                        counter++;
                        Itinerary newItin = new Itinerary();
                        newItin.setItineraryId(firstFlights.size()+1);
                        newItin.setDepartureCity(firstFlight.getDepartureCity());
                        newItin.setDepartureDate(firstFlight.getDepartureDate());
                        newItin.setDestination(destination);
                        newItin.setFirstFlight(firstFlight);
                        newItin.setSecondFlight(flight);
                        results.add(newItin);
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        if(counter != 0)
            return results;
        else
            return null;
    }
    
    public static ArrayList<Itinerary> findDepartingFlights(Date departureDate, String departureCity, String destination) {
        initData();
        ArrayList<Itinerary> searchResults = new ArrayList<>();
        
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date today = Calendar.getInstance().getTime();        
        String reportDate = df.format(today);

        for(Flight flight : flights) {
            String strFlightDepartDate = df.format(flight.getDepartureDate());
            String strDepartureDate = df.format(departureDate);
            
            if(strFlightDepartDate.equals(strDepartureDate) && flight.getDepartureCity().equalsIgnoreCase(departureCity)) {
                Itinerary itinerary = new Itinerary(searchResults.size()+1, departureDate, departureCity, destination, flight);
                searchResults.add(itinerary);
            }
        }
        
        if(searchResults.isEmpty())
            return null;
        else
            return searchResults;
    }
    
    public static ArrayList<Itinerary> getAvailableFlights(ArrayList<Itinerary> allFlights) {
        ArrayList<Itinerary> flightResults = new ArrayList<>();
        
        //remove flights that don't have second flights
        for(Itinerary itin : allFlights) {
            if(itin.getSecondFlight() != null) {
                if(itin.getFirstFlight().getAvailableSeats() > 0 && itin.getSecondFlight().getAvailableSeats() > 0)
                    flightResults.add(itin);
            } else {
            	if(itin.getFirstFlight().getAvailableSeats() > 0 )
            		flightResults.add(itin);
            }
        }
        
        if(flightResults.isEmpty())
            return new ArrayList<>();
        else 
            return flightResults;
    }
    
}