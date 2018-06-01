/**
 * 
 */
package method;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import info.ShortCV;

/**
 * @author Chenchen Cheng
 *
 */
public class JaxbXmlParser {
	
	public JaxbXmlParser() throws Exception {
		
	}
	
	public ShortCV parse(String fileName) throws Exception{
		File file = new File(fileName);
		JAXBContext jaxbContext = JAXBContext.newInstance(ShortCV.class);
		
		Unmarshaller jaxUnnarshaller = jaxbContext.createUnmarshaller();
		ShortCV shortCV = (ShortCV)jaxUnnarshaller.unmarshal(file);
		System.out.println(shortCV.getRecommenderName());
		return shortCV;
		
	}
	/*public static void main(String[] args) throws Exception{
		JaxbXmlParser parser = new JaxbXmlParser();
		String fileName = "src/xml/ShortCVXMLSchema.xml";
		parser.parse(fileName);
	}*/
}
