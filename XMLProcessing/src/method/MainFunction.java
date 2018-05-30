/**
 * 
 */
package method;


import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import info.ApplicantProfile;
import info.CompanyInfoPo;
import info.EmploymentRecord;
import info.ShortCV;
import info.Transcript;

/**
 * @author Chenchen Cheng
 *
 */
public class MainFunction {
	
	public static void main(String[] args){
		try {
			ApplicantProfile applicantProfile = new ApplicantProfile();
			
			//get short CV information
			ShortCV shortCV = new ShortCV();
			JaxbXmlParser parserForCV = new JaxbXmlParser();
			String fileName = "src/xml/ShortCVXMLSchema.xml";
			shortCV = parserForCV.parse(fileName);
			applicantProfile.setFirstName(shortCV.getFirstName());
			applicantProfile.setLastName(shortCV.getLastName());
			applicantProfile.setPersonalNumber(shortCV.getPersonalNumber());
			applicantProfile.setGender(shortCV.getGender());
			applicantProfile.setNationality(shortCV.getNationality());
			applicantProfile.setEmail(shortCV.getEmail());
			applicantProfile.setTelephone(shortCV.getTelephone());
			applicantProfile.setLinkedinUrl(shortCV.getLinkedinUrl());
			applicantProfile.setAchievement(shortCV.getAchievement());
			applicantProfile.setAddress(shortCV.getAddress());
			applicantProfile.setBirthday(shortCV.getBirthday());
			applicantProfile.setSkills(shortCV.getSkills());
			applicantProfile.setMotivationExplanation(shortCV.getMotivationExplanation());
			applicantProfile.setJobType(shortCV.getJobType());
			applicantProfile.setFirstChoice(shortCV.getFirstChoice());
			applicantProfile.setRecommenderName(shortCV.getRecommenderName());
			applicantProfile.setRelation(shortCV.getRelation());
			applicantProfile.setOrganization(shortCV.getOrganization());
			applicantProfile.setContent(shortCV.getContent());
			
			//get transcript information
			//Format transcript.xml and calculate GPA
			XsltTemplateProcessor xsltProcessor = new XsltTemplateProcessor();
			xsltProcessor.convertXMLToHTML("src/xml/transcript.xml", "src/xml/TranscriptTemplate.xsl","src/xml/transcriptOutput.xml");
			SaxXmlParser parserForTranscript = new SaxXmlParser();
			Transcript transcript = new Transcript();
			transcript = parserForTranscript.parse("src/xml/transcriptOutput.xml");
			List<Transcript> transcriptList = applicantProfile.getTranscriptList();
			transcriptList.add(transcript);
			applicantProfile.setTranscriptList(transcriptList);
			
			//get employment record
			SaxXmlParserEmployment handler = new SaxXmlParserEmployment();
            List<EmploymentRecord> employmentRecord = handler.parse("src/xml/employmentRecord.xml");
            applicantProfile.setEmploymentList(employmentRecord);

			
			//get company information
			List<CompanyInfoPo> companyInfoList = applicantProfile.getCompanyInfoList();
			DomXmlParser sw = new DomXmlParser();
			companyInfoList = sw.parse("src/xml/CompanyInfoXMLSchema.xml");
			applicantProfile.setCompanyInfoList(companyInfoList);
			
			//JAXBMarshal to get applicant profile xml
			JaxbXmlMarshal mar = new JaxbXmlMarshal();
			mar.marshaller("src/xml/ApplicantProfile.xml", applicantProfile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
