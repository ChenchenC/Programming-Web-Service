/**
 * 
 */
package info;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ShortCV") 

/**
 * @author Chenchen Cheng
 *
 */
public class ShortCV {
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
	
	@XmlElement(name="FirstName")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement(name="LastName")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlElement(name="PersonalNumber")
	public String getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}
	@XmlElement(name="Gender")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@XmlElement(name="Birthday")
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@XmlElement(name="Nationality")
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	@XmlElement(name="Address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@XmlElement(name="Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement(name="Telephone")
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@XmlElement(name="LinkedinUrl")
	public String getLinkedinUrl() {
		return linkedinUrl;
	}
	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}
	
	@XmlElement(name="Achievements")
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	@XmlElement(name="Skills")
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	@XmlElement(name="MotivationExplanation")
	public String getMotivationExplanation() {
		return motivationExplanation;
	}
	public void setMotivationExplanation(String motivationExplanation) {
		this.motivationExplanation = motivationExplanation;
	}
	@XmlElement(name="JobType")
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	@XmlElement(name="DesiredWorkplace")
	public String getFirstChoice() {
		return firstChoice;
	}
	public void setFirstChoice(String firstChoice) {
		this.firstChoice = firstChoice;
	}
	//@XmlElement(name="SecondChoice")
	public String getSecondChoice() {
		return secondChoice;
	}
	public void setSecondChoice(String secondChoice) {
		this.secondChoice = secondChoice;
	}
	@XmlElement(name="RecommenderName")
	public String getRecommenderName() {
		return recommenderName;
	}
	public void setRecommenderName(String recommenderName) {
		this.recommenderName = recommenderName;
	}
	@XmlElement(name="Relation")
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	@XmlElement(name="Organization")
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	@XmlElement(name="Content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
