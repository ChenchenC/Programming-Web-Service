/**
 * LoginSOAPImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package serviceImplement;

import java.util.ArrayList;
import java.util.List;

public class LoginSOAPImpl implements serviceImplement.Login {

	public static List<BookingIssue> bookingList = new ArrayList<BookingIssue>();

	public java.lang.String loginCheck(java.lang.String userName, java.lang.String password)
			throws java.rmi.RemoteException {
		UserInfoImplement userData = new UserInfoImplement();

		// parameters: 0-non registered user; 1-registered user
		String returnValue = "non-registered user";

		List<UserInfo> userList = userData.getUserList();

		if (null != userList && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {
				UserInfo user = userList.get(i);
				if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
					returnValue = "login successfully";
				}
			}
		}

		return returnValue;
	}

	public java.lang.String bookTicket(java.lang.String flightNumber, java.lang.String userId,
			java.lang.String creditCardNumber) throws java.rmi.RemoteException {
		String bookingNumber = "0";
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

		return bookingNumber;
	}

	public java.lang.String issueTicket(java.lang.String bookingNumber) throws java.rmi.RemoteException {
		String returnValue = "fault";

		TicketInfoImplement ticketInfo = new TicketInfoImplement();
		List<TicketInfo> ticketList = ticketInfo.getTicketList();

		if (null != bookingList && bookingList.size() > 0) {
			for (int i = 0; i < bookingList.size(); i++) {
				if (bookingNumber.equals(bookingList.get(i).getBookingId())) {
					String flightNumber = bookingList.get(i).getFlightNumber();
					for (int j = 0; j < ticketList.size(); j++) {
						if (flightNumber.equals(ticketList.get(j).getFlightNumber())) {
							int ticketNumber = ticketList.get(j).getNumber();
							if (ticketNumber >= 1) {
								ticketNumber -= 1;
								ticketList.get(j).setNumber(ticketNumber);
								returnValue = "successfully";
							} else {

								ticketList.remove(j);
								i--;

							}
						}
					}

				}
			}
		}

		return returnValue;

	}

}
