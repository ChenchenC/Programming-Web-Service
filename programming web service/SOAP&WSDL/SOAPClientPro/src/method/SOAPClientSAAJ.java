/**
 * 
 */
package method;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

/**
 * @author Chenchen Cheng
 *
 */
public class SOAPClientSAAJ {

	public static void main(String args[]) throws Exception {
        // Create SOAP Connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        SOAPConnection soapConnection = soapConnectionFactory.createConnection();

        // Send SOAP Message to SOAP Server
        //TopDowm Service
        String url1 = "http://localhost:8080/TopDownService1/services/LoginSOAPPort";
        //BottomUpService
        String url2 = "http://localhost:8080/BottomUpForService234/services/FlightScannerWS";
        
        SOAPMessage soapResponse = soapConnection.call(createSOAPRequest4(), url2);

        // print SOAP Response
        System.out.print("Response SOAP Message:");
        soapResponse.writeTo(System.out);

        soapConnection.close();
    }
	
	
	 private static SOAPMessage createSOAPRequest1() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://serviceImplement";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ser", serverURI);

	        /*
	        Constructed SOAP Request Message:
	        
	        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://serviceImplement">
   				<soapenv:Header/>
   <soapenv:Body>
      <ser:loginCheck>
         <userName>Sansa</userName>
         <password>300000</password>
      </ser:loginCheck>
   </soapenv:Body>
</soapenv:Envelope>
	         */

	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("loginCheck", "ser");
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("userName", "ser");
	        soapBodyElem1.addTextNode("Sansa");
	        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("password", "ser");
	        soapBodyElem2.addTextNode("300000");

	        //MimeHeaders headers = soapMessage.getMimeHeaders();
	        //headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }
	 
	 private static SOAPMessage createSOAPRequest5() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://serviceImplement";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ser", serverURI);

	        /*
	        Constructed SOAP Request Message:
	        
	  <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://serviceImplement">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:bookTicket>
         <ser:flightNumber>01</ser:flightNumber>
         <ser:userId>000001</ser:userId>
         <ser:creditCardNumber>1</ser:creditCardNumber>
      </ser:bookTicket>
   </soapenv:Body>
</soapenv:Envelope>
	         */

	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("bookTicket", "ser");
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("flightNumber", "ser");
	        soapBodyElem1.addTextNode("01");
	        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("userId", "ser");
	        soapBodyElem2.addTextNode("000001");
	        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("creditCardNumber", "ser");
	        soapBodyElem3.addTextNode("1");

	        //MimeHeaders headers = soapMessage.getMimeHeaders();
	        //headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }
	 
	 private static SOAPMessage createSOAPRequest6() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://serviceImplement";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("ser", serverURI);

	        /*
	        Constructed SOAP Request Message:
	        
	   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://serviceImplement">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:issueTicket>
         <ser:bookingNumber>?</ser:bookingNumber>
      </ser:issueTicket>
   </soapenv:Body>
</soapenv:Envelope>
	         */

	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("issueTicket", "ser");
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("bookingNumber", "ser");
	        soapBodyElem1.addTextNode("01001");


	        //MimeHeaders headers = soapMessage.getMimeHeaders();
	        //headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }
	 
	 private static SOAPMessage createSOAPRequest2() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://method";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("met", serverURI);
	        
/*	        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:met="http://method">
	        <soapenv:Header/>
	        <soapenv:Body>
	           <met:computeItineraryPrice>
	              <met:departureCity>Stockholm</met:departureCity>
	              <met:destination>London</met:destination>
	              <met:departureDateStr>07/02/2017</met:departureDateStr>
	           </met:computeItineraryPrice>
	        </soapenv:Body>
	     </soapenv:Envelope>*/

	        // SOAP Body for service 2
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement itinerary = soapBody.addChildElement("getFlights", "met");
	        SOAPElement departureCity = itinerary.addChildElement("departureCity", "met");
	        departureCity.addTextNode("Stockholm");
	        SOAPElement destination = itinerary.addChildElement("destination", "met");
	        destination.addTextNode("Paris");
	        SOAPElement departureDate = itinerary.addChildElement("departureDateStr", "met");
	        departureDate.addTextNode("07/02/2017");

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", serverURI  + "getFlights");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }

	private static SOAPMessage createSOAPRequest3() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://method";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("met", serverURI);

	        // SOAP Body for service 2
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement itinerary = soapBody.addChildElement("getAvailableFlights", "met");
	        SOAPElement departureCity = itinerary.addChildElement("departureCity", "met");
	        departureCity.addTextNode("Stockholm");
	        SOAPElement destination = itinerary.addChildElement("destination", "met");
	        destination.addTextNode("Paris");
	        SOAPElement departureDate = itinerary.addChildElement("departureDateStr", "met");
	        departureDate.addTextNode("07/02/2017");

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", serverURI  + "getAvailableFlights");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }

	private static SOAPMessage createSOAPRequest4() throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();

	        String serverURI = "http://method";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("met", serverURI);

	        // SOAP Body for service 2
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement itinerary = soapBody.addChildElement("computeItineraryPrice", "met");
	        SOAPElement departureCity = itinerary.addChildElement("departureCity", "met");
	        departureCity.addTextNode("Stockholm");
	        SOAPElement destination = itinerary.addChildElement("destination", "met");
	        destination.addTextNode("Paris");
	        SOAPElement departureDate = itinerary.addChildElement("departureDateStr", "met");
	        departureDate.addTextNode("07/02/2017");

	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", serverURI  + "computeItineraryPrice");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message:");
	        soapMessage.writeTo(System.out);
	        System.out.println();

	        return soapMessage;
	    }

}
