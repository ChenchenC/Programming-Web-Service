/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author shuhnel
 */
public class Module {
    private String transcriptId;
    private String moduleName;
    private String moduleCodePrefix;
    private String moduleCodeSuffix;
    private String moduleGrade;
    private String moduleCredits;
    private String moduleTerm;
    private String moduleYear;
    private String moduleCode;
    
    public Module() {
    
    }
    
    public String getTranscriptId() {
        return transcriptId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleCodePrefix() {
        return moduleCodePrefix;
    }

    public String getModuleCodeSuffix() {
        return moduleCodeSuffix;
    }

    public String getModuleGrade() {
        return moduleGrade;
    }

    public String getModuleCredits() {
        return moduleCredits;
    }

    public String getModuleTerm() {
        return moduleTerm;
    }

    public String getModuleYear() {
        return moduleYear;
    }
    
    public void setTranscriptId(String transcriptId) {
        this.transcriptId = transcriptId;
    }
    
    @XmlElement
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    @XmlElement
    public void setModuleCodePrefix(String moduleCodePrefix) {
        this.moduleCodePrefix = moduleCodePrefix;
    }
    
    @XmlElement
    public void setModuleCodeSuffix(String moduleCodeSuffix) {
        this.moduleCodeSuffix = moduleCodeSuffix;
    }
    
    @XmlElement
    public void setModuleGrade(String moduleGrade) {
        this.moduleGrade = moduleGrade;
    }
    
    @XmlElement
    public void setModuleCredits(String moduleCredits) {
        this.moduleCredits = moduleCredits;
    }
    
    @XmlElement
    public void setModuleTerm(String moduleTerm) {
        this.moduleTerm = moduleTerm;
    }
    
    @XmlElement
    public void setModuleYear(String moduleYear) {
        this.moduleYear = moduleYear;
    }
    
    public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public Module(String transcriptId, String moduleName, String moduleCodePrefix, String moduleCodeSuffix, String moduleGrade, String moduleCredits, String moduleTerm, String moduleYear, String moduleCode) {
        this.transcriptId = transcriptId;
        this.moduleName = moduleName;
        this.moduleCodePrefix = moduleCodePrefix;
        this.moduleCodeSuffix = moduleCodeSuffix;
        this.moduleGrade = moduleGrade;
        this.moduleCredits = moduleCredits;
        this.moduleTerm = moduleTerm;
        this.moduleYear = moduleYear;
        this.moduleCode = moduleCode;
    }
    
}
