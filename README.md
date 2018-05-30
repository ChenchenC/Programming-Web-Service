# Programming-Web-Service
XMLProcessing
  1. Create a schema(XSD) file for each XML document(Transcript, Employment Record, Company Info, short CV and Applicant Profile).
  2. Generate sample documents(XMLs) out of those schema and populate the content(assume that each applicant has at least one         previous. working experience, one academic degree).
  3. Use XSLT to caculate the GPA from the transcript and to put it in an approriate place in user profile in XML format.

  Packages:
  Info: This package stores the java beans
  Method: This package stores the processing methods
  Xml: this package stores the schemas, xml files and the xsl file.

  Outputs:
  1.ApplicantProfile.xml 
  2.TranscriptOutput.xml

  XSL:
  1.TranscriptTemplate.xsl  

  XML file	Processing method
  Transcript.xml	      XSLT (TranscriptTemplate.xslt & XsltTemplateProcessor.java)
  EmploymentRecord.xml	SAX (SaxXmlParserEmployment.java)
  CompanyInfo.xml	      DOM (DomXmlParser.java)
  ApplicantProfile.xml	JAXB (JaxbXmlMarshal.java)
  ShortCVXMLSchema.xml	JAXB (JaxbXmlParser.java)
  TranscriptOutput.xml	SAX (SaxXmlParser.java)


Use Case:
  Flight Ticket Reservation Services Project Tasks
  1. Customer Authorization: check if the inputed user name and password are included in the registration list. If found then give a reply that allows system access.
  2. Check pissible itineries for flights given a departure and a destination city. If there is not direct flight, routes that combine several flights can be given.
  3. Check the availability of tickets and find their price for a given itinerary and given date.
  4. Book the tickets for requested itinerary. Credit card number is required.
  5. Issue Ticket(s)

SOAP & WSDL Implementation
  a)TopDownForApproachPro (tasks 1,4 and 5)
  b)BottomUpForApproachPro (task 2,3)
  c)SoapClientPro (to send the SOAP message requests)


RESTful Web Service
  Flight Ticket
  Packages
  Resources - This package contains the web services logic implementation
  1.LoginCheck.java – Task 1 login implementation 
  2.Searching.java – Task 2,3 searching for flights, availability and its prices
  3.Booking.java – Task 4,5 booking the tickets and the issuance of the ticket

  Client – This package contains the web service clients
  1.ServiceClient.java – To build the specific client for the different services (tasks 1-5)
  2.ServiceTest.java – The main method to test the clients and web services

  Bean – This package contains the java bean classes and the implementation classes to populate the data.

  Web Service Syntactic and Semantic Matching
  Packages
  Calculation package
  This packages contains the classes that are used to calculate the syntactic score.
  EditDistance.java –  the java class which is given to calculate the score of syntactic matching.

  Matching package
  MatchedWebServiceList.java - This java file contains a list of MatchedWebService java. beans to generate the output. This is used by JAXB to generate the output file.
  MatchedElement.java – Java bean of the matched element.
  MatchedOperation.java – Java bean of matched operation.
  MatchedWebService.java – Java bean of matched web service.
  MatchingProcessEasywsdl.java – This class contains the logic to parse the WSDL files.
  CheckTypes.java – This java class filter unusual element types.  
  Test.java – This java class tests the matching process using easy WSDL. 

Ontology package
This package contains methods for semantic matching calculation.
OntologyManager.java - This class is the calculation algorithm which is given. 
OntologyMatcher.java - This class is the implementation of semantic matching.

WSDLs package
This package contains all the given WSDL files for syntactic matching.

SAWSDLs package
This package contains all the given WSDL files for semantic matching.

SRC package
This package contains Output.xsd, SUMO.owl, and all the packages mentioned above.
