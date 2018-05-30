/**
 * 
 */
package method;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import info.EmploymentRecord;

/**
 * @author Chenchen Cheng
 *
 */
public class SaxXmlParserEmployment {

	protected EmploymentRecord employmentRecord;
	protected List<EmploymentRecord> employmentRecordList;
        
	public SaxXmlParserEmployment() throws Exception {

	}
        
        public List<EmploymentRecord> parse(String fileName) {
            employmentRecord = new EmploymentRecord();
            employmentRecordList = new ArrayList<EmploymentRecord>();
            
            try {
                
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();

                DefaultHandler handler = new DefaultHandler() {
                    
                    boolean bCompany, bStartOfEmployment, bEndOfEmployment, 
                        bDepartment, bPosition, bSalary, bJobDesc, bJobType, bJobRecord = false;

                    public void startElement(String uri, String localName, String qName, Attributes attributes) {
                        String prefix = "tns:";

                        if(qName.equalsIgnoreCase(prefix + "jobrecord")) {
                            bJobRecord = true;
                        } else if (qName.equalsIgnoreCase(prefix + "company")) {
                            bCompany = true;
                        } else if (qName.equalsIgnoreCase(prefix + "startOfEmployment")) {
                            bStartOfEmployment = true;
                        } else if (qName.equalsIgnoreCase(prefix + "endOfEmployment")) {
                            bEndOfEmployment = true;
                        } else if (qName.equalsIgnoreCase(prefix + "department")) {
                            bDepartment = true;
                        } else if (qName.equalsIgnoreCase(prefix + "position")) {
                            bPosition = true;
                        } else if (qName.equalsIgnoreCase(prefix + "salary")) {
                            bSalary = true;
                        } else if (qName.equalsIgnoreCase(prefix + "jobDesc")) {
                            bJobDesc = true;
                        } else if (qName.equalsIgnoreCase(prefix + "jobType")) {
                            bJobType = true;
                        }
                    }

                    public void characters(char[] ch, int start, int length) {
                        System.out.println(new String(ch, start, length).trim());
                        
                        if (bJobRecord) {
                            employmentRecordList.add(new EmploymentRecord());
                            bJobRecord = false;
                        } else if(bCompany) {
                            employmentRecordList.get(employmentRecordList.size()-1).setCompany(new String(ch, start, length));
                            bCompany = false;
                        }else if(bStartOfEmployment){
                            employmentRecordList.get(employmentRecordList.size()-1).setStartOfEmployment(new String(ch, start, length));
                            bStartOfEmployment = false;
                        }else if(bEndOfEmployment){
                            employmentRecordList.get(employmentRecordList.size()-1).setEndOfEmployment(new String(ch, start, length));
                            bEndOfEmployment = false;
                        }else if(bDepartment){
                            employmentRecordList.get(employmentRecordList.size()-1).setDepartment(new String(ch, start, length));
                            bDepartment = false;
                        }else if(bPosition){
                            employmentRecordList.get(employmentRecordList.size()-1).setPosition(new String(ch, start, length));
                            bPosition = false;
                        }else if(bSalary){
                            employmentRecordList.get(employmentRecordList.size()-1).setSalary(new String(ch, start, length));
                            bSalary = false;
                        }else if(bJobDesc){
                            employmentRecordList.get(employmentRecordList.size()-1).setJobDesc(new String(ch, start, length));
                            bJobDesc = false;
                        }else if(bJobType){
                            employmentRecordList.get(employmentRecordList.size()-1).setJobType(new String(ch, start, length));
                            bJobType = false;
                        }
                    }

                    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
                        System.out.println("</" + qName + ">");
                    }   
                    
                };
                
                saxParser.parse(fileName, handler);
                
            } catch(Exception e) {
                e.printStackTrace();
            }
            
            return employmentRecordList;
        }

	/*public static void main(String args[]) throws Exception {
		SaxXmlParserEmployment handler = new SaxXmlParserEmployment();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(new File("src/xml/employmentRecord.xml"), handler);
	}*/
}
