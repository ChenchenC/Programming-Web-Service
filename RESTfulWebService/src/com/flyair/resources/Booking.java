/**
 * 
 */
package com.flyair.resources;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flyair.bean.BookingIssue;
import com.flyair.bean.TicketInfo;
import com.flyair.bean.TicketInfoImplement;

/**
 * @author Chenchen Cheng
 *
 */
@Path("/booking")
public class Booking {

	public static List<BookingIssue> bookingList = new ArrayList<BookingIssue>();

	@Path("/bookTicket")
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_XML)
	public Response bookTicket(BookingIssue booking) throws java.rmi.RemoteException {

		String flightNumber = booking.getFlightNumber();
		String userId = booking.getUserId();
		String creditCardNumber = booking.getCreditCardNumber();

		String bookingNumber = "failed";
		TicketInfoImplement ticketInfo = new TicketInfoImplement();
		List<TicketInfo> ticketList = ticketInfo.getTicketList();
		BookingIssue bookingInfo = new BookingIssue();

		if (null != ticketList && ticketList.size() > 0) {
			for (int i = 0; i < ticketList.size(); i++) {
				if (flightNumber.equals(ticketList.get(i).getFlightNumber())) {
					if (null != creditCardNumber && !creditCardNumber.equals("")) {
						bookingNumber = flightNumber + "001";
						bookingInfo.setBookingId(bookingNumber);
						bookingInfo.setFlightNumber(flightNumber);
						bookingInfo.setUserId(userId);
						bookingList.add(bookingInfo);
					}
				}
			}
		}

		return Response.ok().entity(bookingNumber).build();
	}

	@Path("/issueTicket")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response issueTicket(BookingIssue booking) throws RemoteException {

		TicketInfo boughtTicket = new TicketInfo();
		
		String bookingNumber = booking.getBookingId();

		TicketInfoImplement ticketInfo = new TicketInfoImplement();
		List<TicketInfo> ticketList = ticketInfo.getTicketList();
		
		BookingIssue example = new BookingIssue();
		example.setBookingId(bookingNumber);
		example.setFlightNumber("01");
		example.setUserId("000001");
		bookingList.add(example);

		if (null != bookingList && bookingList.size() > 0) {
			for (int i = 0; i < bookingList.size(); i++) {
				if (bookingNumber.equals(bookingList.get(i).getBookingId())) {
					String flightNumber = bookingList.get(i).getFlightNumber();
					for (int j = 0; j < ticketList.size(); j++) {
						if (flightNumber.equals(ticketList.get(j).getFlightNumber())) {
							int ticketNumber = ticketList.get(j).getNumber();
							if (ticketNumber >= 1) {
								ticketNumber = ticketNumber - 1;
								ticketList.get(j).setNumber(ticketNumber);
								boughtTicket = ticketList.get(j);
								break;
							} else {
								ticketList.get(j).setNumber(-1);
								boughtTicket = ticketList.get(j);
								ticketList.remove(j);
								break;

							}
						}
					}

				}
			}
		}

		return Response.ok().entity(boughtTicket).build();

	}

	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/deleteBooking/{bookingNumber}")
	public Response deleteBookingInfo (@PathParam("bookingNumber") String bookingNumber){
		
		String message = "";
		
		if (null != bookingList && bookingList.size() > 0) {
			for (int i = 0; i < bookingList.size(); i++) {
				if (bookingNumber.equals(bookingList.get(i).getBookingId())) {
					bookingList.remove(i);
					message = "Your booking information has been deleted successfully.";
					break;

				}else if(i == bookingList.size()-1){
					message = "Your booking information does not exist.";
				}
			}
		}
		
		return Response.ok().entity(message).build();
	}
}
