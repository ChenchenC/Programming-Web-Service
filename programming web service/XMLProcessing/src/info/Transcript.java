/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shuhnel
 */
@XmlRootElement
public class Transcript {
    private String transcriptId;
    private String firstName, lastName;
    private String university;
    private String degree;
    private String dateOfGraduation;
    private String dateOfEnrolment;
    private String gradePointAverage;
    private ArrayList<Module> moduleList;
    private String name;

    public Transcript(String name, String transcriptId, String firstName, String lastName, String university, String degree, String dateOfGraduation, String dateOfEnrolment, String gradePointAverage, ArrayList<Module> moduleList) {
        this.transcriptId = transcriptId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.university = university;
        this.degree = degree;
        this.dateOfGraduation = dateOfGraduation;
        this.dateOfEnrolment = dateOfEnrolment;
        this.gradePointAverage = gradePointAverage;
        this.moduleList = moduleList;
        this.name = name;
    }
    
    public Transcript() {
        
    }
    
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @XmlAttribute
    public void setTranscriptId(String transcriptId) {
        this.transcriptId = transcriptId;
    }

    @XmlElement
    public void setUniversity(String university) {
        this.university = university;
    }
    
    @XmlElement
    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    @XmlElement
    public void setDateOfGraduation(String dateOfGraduation) {
        this.dateOfGraduation = dateOfGraduation;
    }
    
    @XmlElement
    public void setDateOfEnrolment(String dateOfEnrolment) {
        this.dateOfEnrolment = dateOfEnrolment;
    }

    @XmlElement
    public void setGradePointAverage(String gradePointAverage) {
        this.gradePointAverage = gradePointAverage;
    }
    
    @XmlElement
    public void setModuleList(ArrayList<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public String getTranscriptId() {
        return transcriptId;
    }

    public String getUniversity() {
        return university;
    }

    public String getDegree() {
        return degree;
    }

    public String getDateOfGraduation() {
        return dateOfGraduation;
    }

    public String getDateOfEnrolment() {
        return dateOfEnrolment;
    }

    public String getGradePointAverage() {
        return gradePointAverage;
    }

    public ArrayList<Module> getModuleList() {
        return moduleList;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    
    
}
