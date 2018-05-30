/**
 * 
 */
package com.flyair.client;


/**
 * @author Chenchen Cheng
 *
 */
public class ServiceTest {
	public static void main(String[] args) {
		
		ServiceClient serviceClient = new ServiceClient();
		
		
		//task 1 web service of logging check
		serviceClient.loginCheck("Jon", "000000");// userName password
		
		//task 5 web service of booking ticket
		//serviceClient.bookTicket("01", "000001", "1");//flightNumber userId creditCardNumber
		
		//task 6 web service of issuing ticket
		//serviceClient.issueTicket("01001");//bookingNumber
		
		//task 2 web service of searching flights
		//serviceClient.searchFlight("Stockholm", "London", "2017-02-28");//departure destination date
		
		//task 3,4 web service of checking availability
		//serviceClient.checkAvailability("02", "03");// flightNumber1 flightNumber2
		
	}

}
