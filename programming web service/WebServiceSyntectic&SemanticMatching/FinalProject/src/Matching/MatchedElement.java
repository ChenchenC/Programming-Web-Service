/**
 * 
 */
package Matching;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import Calculation.EditDistance;


/**
 * @author Chenchen Cheng
 *
 */
@XmlRootElement(namespace = "http://www.kth.se/ict/id2208/Matching",name="MatchedElements")
@XmlAccessorType(XmlAccessType.FIELD)
class MatchedElement {
	
	@XmlElement(name = "OutputElement")
    private String outputElement;
	@XmlElement(name = "InputElement")
    private String inputElement;
	@XmlElement(name = "Score")
    private double score;


    public double calculateScoreUsingEditDistance(String outputElement, String inputElement) {
        score = EditDistance.getSimilarity(inputElement, outputElement);
        return score;
    }

    public String getInputElement() {
        return inputElement;
    }

    public String getOutputElement() {
        return outputElement;
    }

    public double getScore() {
        return score;
    }

    public void setInputElement(String inputElement) {
        this.inputElement = inputElement;
    }

    public void setOutputElement(String outputElement) {
        this.outputElement = outputElement;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
