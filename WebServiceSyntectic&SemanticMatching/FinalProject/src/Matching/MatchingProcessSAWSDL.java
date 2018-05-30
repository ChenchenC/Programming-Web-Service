/**
 * 
 */
package Matching;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


import org.xml.sax.InputSource;
import org.ow2.easywsdl.extensions.sawsdl.SAWSDLFactory;
import org.ow2.easywsdl.extensions.sawsdl.api.Description;
import org.ow2.easywsdl.extensions.sawsdl.api.Endpoint;
import org.ow2.easywsdl.extensions.sawsdl.api.Operation;
import org.ow2.easywsdl.extensions.sawsdl.api.SAWSDLReader;
import org.ow2.easywsdl.extensions.sawsdl.api.Service;
import org.ow2.easywsdl.extensions.sawsdl.api.schema.Schema;
import org.ow2.easywsdl.extensions.sawsdl.api.schema.Type;
import org.ow2.easywsdl.wsdl.api.Part;

import Matching.ontology.OntologyMatcher;

/**
 * @author Chenchen Cheng
 *
 */
public class MatchingProcessSAWSDL {
	// get matched webservice
		private MatchedWebService getMatchedWebServiceSemantic(Description d_o,
				Description d_i, OntologyMatcher ontologyMatcher) throws Exception {

			MatchedWebService matchedWebService = new MatchedWebService();
			List<MatchedOperation> mol = matchedWebService.getMatchedOperations();

			Service s_o = null;

			for (Service s : d_o.getServices()) {

				s_o = s;
				matchedWebService.setOutputServiceName(s_o.getQName().getLocalPart());

			}

			if (s_o == null) {

				throw new Exception("No services found on the output");

			}

			Endpoint p_o = null;

			for (Endpoint ep : s_o.getEndpoints()) {

				p_o = ep;

			}

			if (p_o == null) {

				throw new Exception("No ports found on the output");

			}

			Service s_i = null;

			for (Service s : d_i.getServices()) {

				s_i = s;

				matchedWebService.setInputServiceName(s_i.getQName().getLocalPart());
			}

			if (s_i == null) {

				throw new Exception("No services found on the input");

			}

			Endpoint p_i = null;

			for (Endpoint ep : s_i.getEndpoints()) {

				p_i = ep;

			}

			if (p_i == null) {

				throw new Exception("No ports found on the input");

			}

			Schema sm_o = null;

			for (Schema sm : d_o.getTypes().getSchemas()) {
				sm_o = sm;
			}

			if (sm_o == null) {
				throw new Exception("No schemas found on the output");
			}

			Schema sm_i = null;

			for (Schema sm : d_i.getTypes().getSchemas()) {
				sm_i = sm;
			}

			if (sm_i == null) {
				throw new Exception("No schemas found on the input");
			}

			List<Type> el_o = sm_o.getTypes();
			
			if (null == el_o || el_o.size() <= 0) {
				throw new Exception("No elements found on the output");
			}

			List<Type> el_i = sm_i.getTypes();
			if (null == el_i || el_i.size() <= 0) {
				throw new Exception("No elements found on the input");
			}
			//OntologyMatcher ontologyMatcher = new OntologyMatcher();
			//ontologyMatcher.initializeOntology();

			for (Operation o_o : d_o.getInterfaces().get(0).getOperations()) {

				for (Operation o_i : d_i.getInterfaces().get(0).getOperations()) {
					MatchedOperation matchedOperation = new MatchedOperation();
					List<MatchedElement> mel = matchedOperation.getMatchedElements();

					matchedOperation.setInputOperationName(o_i.getQName().getLocalPart());
					matchedOperation.setOutputOperationName(o_o.getQName().getLocalPart());
					
					MatchedElement me = new MatchedElement();
					
					String tn_o = "";
					String tn_i = "";
					for (Part e_o : o_o.getOutput().getParts()) {

						String e_oo = e_o.toString();
						String output_element ="";
						if(e_oo.trim().contains("type")){
							String abc = e_oo.substring(e_oo.indexOf("type="),e_oo.length()-1);
							output_element = abc.substring(abc.indexOf("}")+1,abc.length()).trim();
						}else{
							continue;
						}

						for (int i = 0; i < el_o.size(); i++) {
							Type type_o = el_o.get(i);
							if (null == type_o) {
								continue;
							}
							
							String eName = type_o.getQName().getLocalPart();
							if (output_element.equals(eName)) {
								me.setOutputElement(output_element);
								
								List<URI> ul_o = type_o.getModelReference();
								
								String u_o = ul_o.get(0).toString();
								tn_o = u_o.substring(u_o.indexOf("#")+1, u_o.length()).trim();
								break;

							}
						}
						if(!tn_o.equals("")){
							break;
						}
					}
					
					for (Part e_i : o_i.getInput().getParts()) {
						
						String e_ii = e_i.toString();
						String input_element ="";
						if(e_ii.trim().contains("type")){
							String abc = e_ii.substring(e_ii.indexOf("type="),e_ii.length()-1);
							input_element = abc.substring(abc.indexOf("}")+1,abc.length()).trim();
						}else{
							continue;
						}
						

						for (int j = 0; j < el_i.size(); j++) {
							
							Type type_i = el_i.get(j);
							if (null == type_i) {
								continue;
							}
							
							String eName = type_i.getQName().getLocalPart();
							if (input_element.equals(eName)) {
								
								me.setInputElement(input_element);
								List<URI> ul_i = type_i.getModelReference();
								
								String u_i = ul_i.get(0).toString();
								tn_i = u_i.substring(u_i.indexOf("#")+1, u_i.length()).trim();
								break;

							}
						}
						if(!tn_i.equals("")){
							break;
						}
					}
					
					
					if(!tn_i.equals("") && !tn_o.equals("")){
						double score = ontologyMatcher.getScore(tn_o, tn_i);

						if (score > 0.5) {
						
							me.setScore(score);
							mel.add(me);
						}
					}

					if (!mel.isEmpty()) {

						matchedOperation.calculateOpScore(mel);
						mol.add(matchedOperation);

					}
				}

			}
			if (!mol.isEmpty()) {
				matchedWebService.setMatchedOperations(mol);
				matchedWebService.calculateWSScore(mol);
			}

			return matchedWebService;

		}
		
		public void wsdlParserSemantic(String url) throws Exception {

			MatchedWebServiceList matchedWebServiceList = new MatchedWebServiceList();
			List<MatchedWebService> mwsl = matchedWebServiceList.getMatchedWebServices();
			MatchedWebService matchedWebService = new MatchedWebService();

			// getting all the WSDL files

			File folder = new File(url);
			File[] listOfFiles = folder.listFiles();
			SAWSDLReader reader = SAWSDLFactory.newInstance().newSAWSDLReader();
			
			OntologyMatcher ontologyMatcher = new OntologyMatcher();
			ontologyMatcher.initializeOntology();
			
			for (int i = 0; i < listOfFiles.length; i++) {
				FileInputStream outPutWs = new FileInputStream(listOfFiles[i]);
				Description d_o;
				try {// file not found problem
					d_o = reader.read(new InputSource(outPutWs));

				} catch (Exception e) {
					continue;
				}

				for (int j = 0; j < listOfFiles.length; j++) {
					if (j != i) {
						FileInputStream inPutWs = new FileInputStream(listOfFiles[j]);
						Description d_i;
						try {
							d_i = reader.read(new InputSource(inPutWs));

						} catch (Exception e) {
							continue;
						}

						matchedWebService = getMatchedWebServiceSemantic(d_o, d_i, ontologyMatcher);

						if (!matchedWebService.getMatchedOperations().isEmpty()) {
							System.out.println(matchedWebService.getOutputServiceName()+"!!!!!!!!!!!"+matchedWebService.getInputServiceName());
							mwsl.add(matchedWebService);
						}

					}

				}
			}
			matchedWebServiceList.setMatchedWebServices(mwsl);
			createOutput(matchedWebServiceList);
		}
		
		private void createOutput(MatchedWebServiceList mwsl) {
			JAXBContext context;
			Marshaller m;
			try {
				context = JAXBContext.newInstance(MatchedWebServiceList.class);

				m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

				m.marshal(mwsl, new File("src/output_semantic.xml"));

			} catch (JAXBException ex) {
				Logger.getLogger(MatchedWebServiceList.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

}
