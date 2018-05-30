/**
 * 
 */
package info;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chenchen Cheng
 *
 */
@XmlRootElement
public class ApplicantProfile {
	private String firstName = "";
	private String lastName = "";
	private String personalNumber = "";
	private String gender = "";
	private String birthday = "";
	private String nationality = "";
	private String address = "";
	private String email = "";
	private String telephone = "";
	private String linkedinUrl = "";
	private String achievement = "";
	private String skills = "";
	private String motivationExplanation = "";
	private String jobType = "";
	private String firstChoice = "";
	private String secondChoice = "";
	private String recommenderName = "";
	private String relation = "";
	private String organization = "";
	private String content = "";

	private List<Transcript> transcriptList = new ArrayList<Transcript>();
	private List<EmploymentRecord> employmentList = new ArrayList<EmploymentRecord>();
	private List<CompanyInfoPo> companyInfoList = new ArrayList<CompanyInfoPo>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getLinkedinUrl() {
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getMotivationExplanation() {
		return motivationExplanation;
	}

	public void setMotivationExplanation(String motivationExplanation) {
		this.motivationExplanation = motivationExplanation;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getFirstChoice() {
		return firstChoice;
	}

	public void setFirstChoice(String firstChoice) {
		this.firstChoice = firstChoice;
	}

	public String getSecondChoice() {
		return secondChoice;
	}

	public void setSecondChoice(String secondChoice) {
		this.secondChoice = secondChoice;
	}

	public String getRecommenderName() {
		return recommenderName;
	}

	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Transcript> getTranscriptList() {
		return transcriptList;
	}

	public void setTranscriptList(List<Transcript> transcriptList) {
		this.transcriptList = transcriptList;
	}

	public List<EmploymentRecord> getEmploymentList() {
		return employmentList;
	}

	public void setEmploymentList(List<EmploymentRecord> employmentList) {
		this.employmentList = employmentList;
	}

	public List<CompanyInfoPo> getCompanyInfoList() {
		return companyInfoList;
	}

	public void setCompanyInfoList(List<CompanyInfoPo> companyInfoList) {
		this.companyInfoList = companyInfoList;
	}

}
