package method;

import info.*;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author shuhnel
 */
public class SaxXmlParser {

   /* public static void main(String[] args) {*/
    public Transcript parse(String fileName){
        
        Transcript transcript = new Transcript();

        System.out.println("SAXXMLParse method");

        try {

            transcript.setModuleList(new ArrayList<Module>());

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler;
            handler = new DefaultHandler() {

                boolean bTranscript, bFirstName, bLastName = false;
                boolean bDateOfGrad, bDateOfEnrol, bModule = false;
                boolean bUni, bGPA, bDegree = false;
                boolean bModuleCodePrefix, bModuleCodeSuffix, bModuleName = false;
                boolean bModuleGrade, bModuleCredit, bModuleTerm, bModuleYear = false;
                int moduleCounter = 0;

                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    String prefix = "tns:";

                    if (qName.equalsIgnoreCase(prefix+"transcript")) {
                        transcript.setTranscriptId(attributes.getValue("id"));
                        bTranscript = true;
                    } else if (qName.equalsIgnoreCase(prefix+"name")) {
                        bFirstName = true;
                    } else if (qName.equalsIgnoreCase(prefix+"lastname")) {
                        bLastName = true;
                    } else if (qName.equalsIgnoreCase(prefix+"dateOfGraduation")) {
                        bDateOfGrad = true;
                    } else if (qName.equalsIgnoreCase(prefix+"dateofEnrolment")) {
                        bDateOfEnrol = true;
                    } else if (qName.equalsIgnoreCase(prefix+"module")) {
                        bModule = true;
                    } else if (qName.equalsIgnoreCase(prefix+"moduleCode")) {
                        bModuleCodePrefix = true;
                    } else if (qName.equalsIgnoreCase(prefix+"moduleCodeSuffix")) {
                        bModuleCodeSuffix = true;
                    } else if (qName.equalsIgnoreCase(prefix+"moduleGrade")) {
                        bModuleGrade = true;
                    } else if (qName.equalsIgnoreCase(prefix+"moduleCredits")) {
                        bModuleCredit = true;
                    } else if (qName.equalsIgnoreCase(prefix+"moduleTerm")) {
                        bModuleTerm = true;
                    } else if (qName.equalsIgnoreCase(prefix+"moduleYear")) {
                        bModuleYear = true;
                    } else if (qName.equalsIgnoreCase(prefix+"moduleName")) {
                        bModuleName = true;
                    }  else if (qName.equalsIgnoreCase(prefix+"GPA")) {
                        bGPA = true;
                    }  else if (qName.equalsIgnoreCase(prefix+"degree")) {
                        bDegree = true;
                    } else if (qName.equalsIgnoreCase(prefix+"university")) {
                        bUni = true;
                    } 
                }

                /*
                This method stores the value embedded in the xml tags
                into the java beans.
                */
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (bFirstName) {
                        System.out.println("First Name : " + new String(ch, start, length));
                        transcript.setName(new String(ch, start, length));
                        bFirstName = false;
                    }else if (bLastName) {
                        System.out.println("Last Name : " + new String(ch, start, length));
                        transcript.setLastName(new String(ch, start, length));
                        bLastName = false;
                    } else if (bDateOfGrad) {
                        System.out.println("Date of Grad : " + new String(ch, start, length));
                        transcript.setDateOfGraduation(new String(ch, start, length));
                        bDateOfGrad = false;
                    } else if (bDateOfEnrol) {
                        System.out.println("Date of Enrol : " + new String(ch, start, length));
                        transcript.setDateOfEnrolment(new String(ch, start, length));
                        bDateOfEnrol = false;
                    }  else if (bDegree) {
                        System.out.println("Degree : " + new String(ch, start, length));
                        transcript.setDegree(new String(ch, start, length));
                        bDegree = false;
                    }  else if (bUni) {
                        System.out.println("University : " + new String(ch, start, length));
                        transcript.setUniversity(new String(ch, start, length));
                        bUni = false;
                    }  else if (bGPA) {
                        System.out.println("GPA : " + new String(ch, start, length));
                        transcript.setGradePointAverage(new String(ch, start, length));
                        bGPA = false;
                    } else if (bModule) {
                        Module module = new Module();
                        module.setTranscriptId(transcript.getTranscriptId());
                        ArrayList<Module> moduleList = transcript.getModuleList();
                        moduleList.add(module);
                        System.out.println("Module Name : " + new String(ch, start, length));
                        moduleList.get(moduleList.size() - 1).setModuleName(new String(ch, start, length));
                        bModule = false;
                    } else if (bModuleCodePrefix) {
                        System.out.println("Module Code Prefix : " + new String(ch, start, length));
                        ArrayList<Module> moduleList = transcript.getModuleList();
                        moduleList.get(moduleList.size() - 1).setModuleCode(new String(ch, start, length));
                        bModuleCodePrefix = false;
                    } else if (bModuleCodeSuffix) {
                        System.out.println("Module Code Suffix : " + new String(ch, start, length));
                        ArrayList<Module> moduleList = transcript.getModuleList();
                        moduleList.get(moduleList.size() - 1).setModuleCodeSuffix(new String(ch, start, length));
                        bModuleCodeSuffix = false;
                    } else if (bModuleGrade) {
                        System.out.println("Module Grade: " + new String(ch, start, length));
                        ArrayList<Module> moduleList = transcript.getModuleList();
                        moduleList.get(moduleList.size() - 1).setModuleGrade(new String(ch, start, length));
                        bModuleGrade = false;
                    } else if (bModuleCredit) {
                        System.out.println("Module Credits : " + new String(ch, start, length));
                        ArrayList<Module> moduleList = transcript.getModuleList();
                        moduleList.get(moduleList.size() - 1).setModuleCredits(new String(ch, start, length));
                        bModuleCredit = false;
                    } else if (bModuleTerm) {
                        System.out.println("Module Term : " + new String(ch, start, length));
                        ArrayList<Module> moduleList = transcript.getModuleList();
                        moduleList.get(moduleList.size() - 1).setModuleTerm(new String(ch, start, length));
                        bModuleTerm = false;
                    } else if (bModuleYear) {
                        System.out.println("Module Year : " + new String(ch, start, length));
                        ArrayList<Module> moduleList = transcript.getModuleList();
                        moduleList.get(moduleList.size() - 1).setModuleYear(new String(ch, start, length));
                        bModuleYear = false;
                    } 
                }

            };
            saxParser.parse(fileName, handler);

            ArrayList<Module> moduleList = transcript.getModuleList();
            for (Module module : moduleList) {
                System.out.println("Module: " + module.getModuleName());  
           }

        } catch (Exception e) {
            e.printStackTrace();
        }

/*        System.out.println("Start of JAXB parse to XML");

         try {

            File file = new File("src/xml/ApplicantProfile.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Transcript.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(transcript, file);
            jaxbMarshaller.marshal(transcript, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        System.out.println("Start of JAXB parse to java bean");

        try {

            File file = new File("src/xml/ApplicantProfile.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Transcript.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Transcript transcript2 = (Transcript) jaxbUnmarshaller.unmarshal(file);
            System.out.println();

            System.out.println("ID: "+ transcript2.getTranscriptId());
            System.out.println("UNi: "+ transcript2.getUniversity());
            System.out.println("degree: "+ transcript2.getDegree());
            System.out.println("moduel list size: "+ transcript2.getModuleList().size());
            Module module1 = transcript2.getModuleList().get(0);
            Module module2 = transcript2.getModuleList().get(1);

            System.out.println("Module 1: "+  module1.getModuleName());
            System.out.println("Module 2: "+  module2.getModuleName());

        } catch (JAXBException e) {
            e.printStackTrace();
        }*/
		return transcript;
    
    } 
    
}
