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

/**
 * @author Chenchen Cheng
 *
 */
@XmlRootElement(namespace = "http://www.kth.se/ict/id2208/Matching", name = "WSMatching")
@XmlAccessorType(XmlAccessType.FIELD)
public class MatchedWebServiceList {
	    @XmlElement(name = "Matching")
	    public List<MatchedWebService> matchedWebServices = new ArrayList<MatchedWebService>();

		public List<MatchedWebService> getMatchedWebServices() {
			return matchedWebServices;
		}

		public void setMatchedWebServices(List<MatchedWebService> matchedWebServices) {
			this.matchedWebServices = matchedWebServices;
		}
	    
}
