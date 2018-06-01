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
@XmlRootElement(name = "Matching", namespace = "http://www.kth.se/ict/id2208/Matching")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "OutputServiceName", "InputServiceName", "wsScore", "matchedOperations" })

public class MatchedWebService {

	@XmlElement(name = "OutputServiceName")
    private String OutputServiceName;
	@XmlElement(name = "InputServiceName")
    private String InputServiceName;
    @XmlElement(name = "WsScore")
    private double wsScore;
    
    @XmlElement(name = "MatchedOperations")
    public List<MatchedOperation> matchedOperations = new ArrayList<MatchedOperation>();

    public String getOutputServiceName() {
        return OutputServiceName;
    }

    public String getInputServiceName() {
        return InputServiceName;
    }

    public double getWsScore() {
        return wsScore;
    }
    
    public void setWsScore(double wsScore) {
		this.wsScore = wsScore;
	}

	public void setInputServiceName(String inputServiceName) {
        this.InputServiceName = inputServiceName;
    }

    public void setOutputServiceName(String outputServiceName) {
        this.OutputServiceName = outputServiceName;
    }

    
    public List<MatchedOperation> getMatchedOperations() {
		return matchedOperations;
	}

	public void setMatchedOperations(List<MatchedOperation> matchedOperations) {
		this.matchedOperations = matchedOperations;
	}

	public double calculateWSScore(List<MatchedOperation> matchedOperations) {
        wsScore = 0;
        if (matchedOperations.isEmpty()) {
            return 0.0;
        }
        
        for (MatchedOperation mo : matchedOperations) {
            wsScore += mo.getOpScore();
        }
        wsScore /= matchedOperations.size();
        return wsScore;
    }
    
}
