/**
 * 
 */
package method;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import info.ApplicantProfile;

/**
 * @author Chenchen Cheng
 *
 */
public class JaxbXmlMarshal {

	public void marshaller (String fileName, ApplicantProfile applicantProfile) throws Exception
	{
		 File file = new File(fileName);
         JAXBContext jaxbContext = JAXBContext.newInstance(ApplicantProfile.class);
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

         // output pretty printed
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

         jaxbMarshaller.marshal(applicantProfile, file);
         jaxbMarshaller.marshal(applicantProfile, System.out);
	}
	
	
}
