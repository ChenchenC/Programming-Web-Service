/**
 * 
 */
package com.flyair.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flyair.bean.TicketInfo;
import com.flyair.bean.TicketInfoImplement;

/**
 * @author Chenchen Cheng
 *
 */
@Path("/searchFlight")
public class Searching {

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("flights/{departure}/{destination}/{datetime}")
	public Response searchFlight(@PathParam("departure") String departure, @PathParam("destination") String destination,
			@PathParam("datetime") String datetime) throws java.rmi.RemoteException {

		TicketInfoImplement ticketImpl = new TicketInfoImplement();
		List<TicketInfo> ticketList = ticketImpl.getTicketList();

		List<TicketInfo> resultTicketList = new ArrayList<TicketInfo>();

		if (null != ticketList && ticketList.size() > 0) {
			for (int i = 0; i < ticketList.size(); i++) {
				if (ticketList.get(i).getDeparture().equals(departure)) {
					String tDestination = ticketList.get(i).getDestination();
					if (tDestination.equals(destination)) {
						if (ticketList.get(i).getDate().equals(datetime)) {
							resultTicketList.add(ticketList.get(i));
						}
					} else {
						for (int j = 0; j < ticketList.size(); j++) {
							if (ticketList.get(j).getDeparture().equals(tDestination)
									&& ticketList.get(j).getDestination().equals(destination)
									&& ticketList.get(j).getDate().equals(datetime)) {
								resultTicketList.add(ticketList.get(i));
								resultTicketList.add(ticketList.get(j));
							}
						}
					}
				}
			}
		}
		String message = "";

		if (null != resultTicketList && resultTicketList.size() > 0) {
			for (int i = 0; i < resultTicketList.size(); i++) {
				message += "No." + resultTicketList.get(i).getFlightNumber() + " from ("
						+ resultTicketList.get(i).getDeparture() + ") to (" + resultTicketList.get(i).getDestination()
						+ ") Date:" + datetime + "; \n";
			}
		} else {
			message = "Sorry. There is not proper flights for you.";
		}

		return Response.ok().entity(message).build();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("checkAvailability/{flightNumber}")
	public Response checkAvailability(@PathParam("flightNumber") String flightNumber) throws java.rmi.RemoteException {

		TicketInfoImplement ticketImpl = new TicketInfoImplement();
		List<TicketInfo> ticketList = ticketImpl.getTicketList();

		List<TicketInfo> resultTicketList = new ArrayList<TicketInfo>();
		
		String flightNumber1 = "";
		String flightNumber2 = "";

		if (flightNumber.length() > 2) {

			flightNumber1 = flightNumber.substring(0, 2);
			flightNumber2 = flightNumber.substring(2);
		}else{
			flightNumber1 = flightNumber;
		}

		if (null != ticketList && ticketList.size() > 0) {
			for (int i = 0; i < ticketList.size(); i++) {
				if (ticketList.get(i).getFlightNumber().equals(flightNumber1)) {
					int number = ticketList.get(i).getNumber();
					if (number > 0) {
						resultTicketList.add(ticketList.get(i));
					}
				}
			}
			if (!flightNumber2.equals("")) {
				for (int i = 0; i < ticketList.size(); i++) {
					if (ticketList.get(i).getFlightNumber().equals(flightNumber2)) {
						int number = ticketList.get(i).getNumber();
						if (number > 0) {
							resultTicketList.add(ticketList.get(i));
						}
					}
				}
			}
		}

		String message = "";

		if (null != resultTicketList && resultTicketList.size() > 0) {

			for (int i = 0; i < resultTicketList.size(); i++) {
				message += "No." + resultTicketList.get(i).getFlightNumber() + " from ("
						+ resultTicketList.get(i).getDeparture() + ") to (" + resultTicketList.get(i).getDestination()
						+ ") Date:" + resultTicketList.get(i).getDate() + "; \n";
			}
			if (resultTicketList.size() == 2) {
				message += "Price:"
						+ String.valueOf(resultTicketList.get(0).getPrice() + resultTicketList.get(1).getPrice())
						+ "kr \n";
			} else {
				message += "Price:" + String.valueOf(resultTicketList.get(0).getPrice()) + "kr \n";
			}
		} else {
			message = "Sorry. The flights are not available at present.";
		}

		return Response.ok().entity(message).build();
	}

}
