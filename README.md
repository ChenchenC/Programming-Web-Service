# Programming-Web-Service
XMLProcessing
1. Create a schema(XSD) file for each XML document(Transcript, Employment Record, Company Info, short CV and Applicant Profile)
2. Generate sample documents(XMLs) out of those schema and populate the content(assume that each applicant has at least one previous working experience, one academic degree).
3. Use XSLT to caculate the GPA from the transcript and to put it in an approriate place in user profile in XML format
Schemas:
1.CompanyInfoXMLSchema.xsd
2.ApplicationProfileXMLSchema.xsd
3.EmploymentRecord.xsd
4.ShortCVXMLSchema.xsd
5.Transcript.xsd

XML files (with the populated data): 
1.CompanyInfoXMLSchema.xml
2.EmploymentRecord.xml
3.ShortCVXMLSchema.xml
4.Transcript.xml

Outputs:
1.ApplicantProfile.xml 
2.TranscriptOutput.xml

XSL:
1.TranscriptTemplate.xsl  

Packages:
Info: This package stores the java beans
Method: This package stores the processing methods
Xml: this package stores the schemas, xml files and the xsl file.

XML file	Processing method
Transcript.xml	      XSLT (TranscriptTemplate.xslt & XsltTemplateProcessor.java)
EmploymentRecord.xml	SAX (SaxXmlParserEmployment.java)
CompanyInfo.xml	      DOM (DomXmlParser.java)
ApplicantProfile.xml	JAXB (JaxbXmlMarshal.java)
ShortCVXMLSchema.xml	JAXB (JaxbXmlParser.java)
TranscriptOutput.xml	SAX (SaxXmlParser.java)
