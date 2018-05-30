/**
 * 
 */
package Matching;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Chenchen Cheng
 *
 */
@XmlRootElement(namespace = "http://www.kth.se/ict/id2208/Matching",name="MatchedOperations")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "OutputOperationName", "InputOperationName", "opScore", "matchedElements" })

public class MatchedOperation {

	@XmlElement(name = "OutputOperationName")
    private String OutputOperationName;
	
	@XmlElement(name = "InputOperationName")
    private String InputOperationName;
    
    @XmlElement(name = "OpScore")
    private double opScore;
    
    @XmlElement(name = "MatchedElements")
    public List<MatchedElement> matchedElements = new ArrayList<MatchedElement>();

    public String getInputOperationName() {
        return InputOperationName;
    }

    public double getOpScore() {
        return opScore;
    }
    

    public void setOpScore(double opScore) {
		this.opScore = opScore;
	}

	public String getOutputOperationName() {
        return OutputOperationName;
    }

    public void setInputOperationName(String inputOperationName) {
        this.InputOperationName = inputOperationName;
    }

    public void setOutputOperationName(String outputOperationName) {
        this.OutputOperationName = outputOperationName;
    }
    
    public List<MatchedElement> getMatchedElements() {
		return matchedElements;
	}

	public void setMatchedElements(List<MatchedElement> matchedElements) {
		this.matchedElements = matchedElements;
	}

	public double calculateOpScore(List<MatchedElement> matchedElements) {
        opScore = 0;
        
        if (matchedElements.isEmpty()) {
            return 0.0;
        }
        
        for (MatchedElement me : matchedElements) {
            opScore += me.getScore();
        }
        opScore /= matchedElements.size();
        return opScore;
    }
    
}
