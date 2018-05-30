/**
 * 
 */
package serviceImplement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chenchen Cheng
 *
 */
public class TicketInfoImplement {
	protected List<TicketInfo> ticketList = new ArrayList<TicketInfo>();
	public List<TicketInfo> getTicketList(){
		TicketInfo ticket1 = new TicketInfo();
		TicketInfo ticket2 = new TicketInfo();
		TicketInfo ticket3 = new TicketInfo();
		TicketInfo ticket4 = new TicketInfo();
		
		ticket1.setFlightNumber("01");
		ticket1.setDeparture("Stockholm");
		ticket1.setDestination("London");
		ticket1.setDate("2017-02-28");
		ticket1.setPrice(500.0);
		ticket1.setNumber(3);
		
		ticket2.setFlightNumber("02");
		ticket2.setDeparture("Stockholm");
		ticket2.setDestination("Brussel");
		ticket2.setDate("2017-02-28");
		ticket2.setPrice(400.0);
		ticket2.setNumber(3);
		
		ticket3.setFlightNumber("03");
		ticket3.setDeparture("Brussel");
		ticket3.setDestination("London");
		ticket3.setDate("2017-02-28");
		ticket3.setPrice(300.0);
		ticket3.setNumber(2);
		
		ticket4.setFlightNumber("04");
		ticket4.setDeparture("Stockholm");
		ticket4.setDestination("Paris");
		ticket4.setDate("2017-02-29");
		ticket4.setPrice(600.0);
		ticket4.setNumber(1);
		
		ticketList.add(ticket1);
		ticketList.add(ticket2);
		ticketList.add(ticket3);
		ticketList.add(ticket4);
		
		return ticketList;
	}

}
