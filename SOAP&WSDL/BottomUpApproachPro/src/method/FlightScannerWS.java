/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method;

import beans.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author shuhnel
 */
@WebService(serviceName = "FlightScannerWS")
public class FlightScannerWS {
    
    protected ArrayList<Itinerary> allFlights = new ArrayList<>();
    protected ArrayList<Itinerary> availableFlights = new ArrayList<>();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "computeItineraryPrice")
    public String computeItineraryPrice(@WebParam(name = "departureCity") String departureCity, 
            @WebParam(name = "destination") String destination,
            @WebParam(name = "departureDate") String departureDateStr) throws ParseException {
        
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date departureDate = format.parse(departureDateStr);
        
        allFlights = FlightsInfo.findFlights(departureDate, departureCity, destination);
        
        availableFlights = FlightsInfo.getAvailableFlights(allFlights);

    	String resultsStr = "";
    	int counter = 0;
        if(availableFlights == null)
            return "";
        else {
        	for (Itinerary itin : availableFlights) {
        		resultsStr +="Flight #" + itin.getFirstFlight().getFlightID() + ": " 
        					+ itin.getFirstFlight().getDepartureCity() + " > " 
        				+ itin.getFirstFlight().getDestination()
        				+ " $" + itin.getFirstFlight().getPrice() + " per pax. ";
        		
        		if(itin.getSecondFlight() != null) {
        			resultsStr +=" Flight #" +  itin.getSecondFlight().getFlightID() + ": " 
        						+ itin.getSecondFlight().getDepartureCity() + " > " 
        					+ itin.getSecondFlight().getDestination()
            				+ " $" + itin.getSecondFlight().getPrice() + " per pax";
        					resultsStr += " TOTAL PRICE: $" + (itin.getFirstFlight().getPrice() + itin.getSecondFlight().getPrice()) + "\n";
        		} else {
        			resultsStr += " TOTAL PRICE: $" + (itin.getFirstFlight().getPrice()) + "\n";
        		}
        	}
        	return resultsStr;
        }
    }
    
    @WebMethod(operationName = "getFlights")
    public String getFlights(@WebParam(name = "departureCity") String departureCity, 
            @WebParam(name = "destination") String destination,
            @WebParam(name = "departureDate") String departureDateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date departureDate = format.parse(departureDateStr);
        
        allFlights = FlightsInfo.findFlights(departureDate, departureCity, destination);

		String resultsStr = "";
        
    	if(allFlights == null)
            return resultsStr;
        else {
        	for (Itinerary itin : allFlights) {
        		resultsStr += "Flight #" + itin.getFirstFlight().getFlightID() + ": " 
        					+ itin.getFirstFlight().getDepartureCity() + " > " 
        				+ itin.getFirstFlight().getDestination()
    					+ " (" + itin.getFirstFlight().getAvailableSeats() + " seats available).";
        		
        		if(itin.getSecondFlight() != null) {
        			resultsStr += " Flight #" + itin.getSecondFlight().getFlightID() + ": " 
        						+ itin.getSecondFlight().getDepartureCity() + " > " 
        						+ itin.getSecondFlight().getDestination()
        						+ " (" + itin.getSecondFlight().getAvailableSeats() + " seats available) \n";
        		}
        		
        	}
        	return resultsStr;
        }
    }
    
    @WebMethod(operationName = "getAvailableFlights")
    public String getAvailableFlights(@WebParam(name = "departureCity") String departureCity, 
            @WebParam(name = "destination") String destination,
            @WebParam(name = "departureDate") String departureDateStr) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date departureDate = format.parse(departureDateStr);
        
        allFlights = FlightsInfo.findFlights(departureDate, departureCity, destination);
        
        availableFlights = FlightsInfo.getAvailableFlights(allFlights);
        
		String resultsStr = "";
       
    	if(availableFlights == null)
            return "";
        else {
        	for (Itinerary itin : availableFlights) {
        		resultsStr  += "Flight #" + itin.getFirstFlight().getFlightID() + ": " 
        					+ itin.getFirstFlight().getDepartureCity() 
        					+ " > " + itin.getFirstFlight().getDestination()
        					+ " (" + itin.getFirstFlight().getAvailableSeats() + " seats available).";
        		
        		if(itin.getSecondFlight() != null) {
        			resultsStr += " Flight #" + itin.getSecondFlight().getFlightID() + ": " 
        						+ itin.getSecondFlight().getDepartureCity() + " > " 
        					+ itin.getSecondFlight().getDestination()
        					+ " (" + itin.getSecondFlight().getAvailableSeats() + " seats available)\n";
        		}
        	}
        	return resultsStr;
        }
    }
    
    
}
