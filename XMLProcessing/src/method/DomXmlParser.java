/**
 * 
 */
package method;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import info.CompanyInfoPo;

/**
 * @author Chenchen Cheng
 *
 */
public class DomXmlParser {
	protected DocumentBuilder docBuilder;
	protected Element root;
	protected CompanyInfoPo companyInfoPo = new CompanyInfoPo();
	public List<CompanyInfoPo> companyInfoList = new ArrayList<CompanyInfoPo>();

	public DomXmlParser() throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		docBuilder = dbf.newDocumentBuilder();
		DOMImplementation domImp = docBuilder.getDOMImplementation();
		if (domImp.hasFeature("XML", "1.0")) {
			System.out.println("XML is going to parse.");
		}
	}

	public List<CompanyInfoPo> parse(String fileName) throws Exception {
		fileName = "src/xml/CompanyInfoXMLSchema.xml";
		
		File inputFile = new File(fileName);
		Document doc = docBuilder.parse(inputFile);
		//Document doc = docBuilder.parse(new FileInputStream(fileName));
		doc.getDocumentElement().normalize();
		root = doc.getDocumentElement();
		System.out.println("Root element is " + root.getNodeName());
		
		NodeList nList = doc.getElementsByTagName("tns:CompanyInfo");
		System.out.println("----------"+nList.getLength());
		for(int i=0; i<nList.getLength(); i++){
			Node nNode = nList.item(i);
			System.out.println("----------" + nNode.getNodeType());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				System.out.println("----------" + element.getAttribute("companyId"));
				companyInfoPo.setCompanyId(element.getAttribute("companyId"));
				companyInfoPo.setSubmitted(element.getAttribute("submitted"));
	
				//Element eCompanyName = element.getElementsByTagName("tns:CompanyName");
				companyInfoPo.setCompanyName(element.getElementsByTagName("tns:CompanyName").item(0).getTextContent());
				companyInfoPo.setLine1(element.getElementsByTagName("tns:Line1").item(0).getTextContent());
				companyInfoPo.setCity(element.getElementsByTagName("tns:City").item(0).getTextContent());
				companyInfoPo.setCountry(element.getElementsByTagName("tns:Country").item(0).getTextContent());
				companyInfoPo.setYearEstablished(element.getElementsByTagName("tns:YearEstablished").item(0).getTextContent());
				companyInfoPo.setTelephone(element.getElementsByTagName("tns:Telephone").item(0).getTextContent());
				companyInfoPo.setFax(element.getElementsByTagName("tns:Fax").item(0).getTextContent());
				companyInfoPo.setPostCode(element.getElementsByTagName("tns:PostCode").item(0).getTextContent());
				companyInfoPo.setEmail(element.getElementsByTagName("tns:Email").item(0).getTextContent());
				companyInfoPo.setWebsite(element.getElementsByTagName("tns:Website").item(0).getTextContent());
				companyInfoPo.setEmployeeNumber(element.getElementsByTagName("tns:EmployeeNumber").item(0).getTextContent());
				companyInfoPo.setCompanyType(element.getElementsByTagName("tns:CompanyType").item(0).getTextContent());
				companyInfoPo.setDescription(element.getElementsByTagName("tns:Description").item(0).getTextContent());
				companyInfoPo.setFirstContactName(element.getElementsByTagName("tns:FirstContactName").item(0).getTextContent());
				companyInfoPo.setFirstContactTitle(element.getElementsByTagName("tns:FirstContactTitle").item(0).getTextContent());
				System.out.println("----------" + companyInfoPo.getCity());
				System.out.println("----------" + companyInfoPo.getYearEstablished());
				companyInfoList.add(companyInfoPo);
			}
		}
		System.out.println("----------" + companyInfoList.size());
		return companyInfoList;

	}


	/*public static void main(String args[]) throws Exception {
		DomXmlParser sw = new DomXmlParser();
		sw.parse("src/xml/CompanyInfoXMLSchema.xml");
		
	}*/
}
