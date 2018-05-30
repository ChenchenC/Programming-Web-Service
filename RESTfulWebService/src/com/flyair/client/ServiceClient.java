/**
 * 
 */
package com.flyair.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flyair.bean.BookingIssue;
import com.flyair.bean.TicketInfo;

/**
 * @author Chenchen Cheng
 *
 */
public class ServiceClient {

	private static String URL = "http://localhost:8080/FlightService/rest";
	private Client client = ClientBuilder.newClient();

	public void loginCheck(String userName, String password) {

		WebTarget webTarget = client.target(URL).path("login/" + userName + "/" + password + "");
		/*
		 * WebTarget webTarget = client.target(URL).path("login")
		 * .queryParam("userName", userName).queryParam("password", password);
		 */

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_PLAIN);

		Response response = invocationBuilder.get();
		System.out.println(response.readEntity(String.class));

		client.close();

	}

	public void bookTicket(String flightNumber, String userId, String creditCardNumber) {

		WebTarget webTarget = client.target(URL).path("booking").path("bookTicket");

		BookingIssue booking = new BookingIssue();
		booking.setFlightNumber(flightNumber);
		booking.setUserId(userId);
		booking.setCreditCardNumber(creditCardNumber);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_XML);

		Response response = invocationBuilder.post(Entity.entity(booking, MediaType.APPLICATION_XML));
		System.out.println(response.readEntity(String.class));

		client.close();

	}

	public void issueTicket(String bookingNumber) {

		WebTarget webTarget = client.target(URL).path("booking").path("issueTicket");

		BookingIssue booking = new BookingIssue();
		booking.setBookingId(bookingNumber);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);

		Response response = invocationBuilder.put(Entity.entity(booking, MediaType.APPLICATION_XML));

		TicketInfo ticket = response.readEntity(TicketInfo.class);
		int ticketNumber = ticket.getNumber();

		// There is no available ticket for this flight.
		if (ticketNumber < 0) {
			// Delete the booking information
			WebTarget webTargetDelete = client.target(URL).path("booking").path("deleteBooking").path(bookingNumber);
			Invocation.Builder invocationBuilderDelete = webTargetDelete.request(MediaType.TEXT_PLAIN);

			Response responseDelete = invocationBuilderDelete.delete();

			System.out.println(responseDelete.readEntity(String.class));

		} else {
			String message = "Your ticket of Flight(" + ticket.getFlightNumber() + ") has been issued. " + "And "
					+ ticket.getNumber() + " ticket(s) left at present.";
			System.out.println(message);
		}

		client.close();

	}

	public void searchFlight(String departure, String destination, String datetime) {

		WebTarget webTarget = client.target(URL).path("searchFlight").path("flights").path(departure).path(destination)
				.path(datetime);
		/*
		 * WebTarget webTarget = client.target(URL).path("login")
		 * .queryParam("userName", userName).queryParam("password", password);
		 */

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_XML);

		Response response = invocationBuilder.get();

		System.out.println(response.readEntity(String.class));

		client.close();

	}

	public void checkAvailability(String flightNumber1, String flightNumber2) {
		
		String flightNumber = flightNumber1 + flightNumber2;

		WebTarget webTarget = client.target(URL).path("searchFlight").path("checkAvailability").path(flightNumber);
		/*
		 * WebTarget webTarget = client.target(URL).path("login")
		 * .queryParam("userName", userName).queryParam("password", password);
		 */

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.TEXT_XML);

		Response response = invocationBuilder.get();

		System.out.println(response.readEntity(String.class));

		client.close();

	}

}
