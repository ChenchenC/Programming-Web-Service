/**
 * 
 */
package Matching;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.ow2.easywsdl.schema.api.Element;
import org.ow2.easywsdl.schema.api.SchemaException;
import org.ow2.easywsdl.schema.api.Sequence;
import org.ow2.easywsdl.schema.api.Type;
import org.ow2.easywsdl.schema.impl.ComplexTypeImpl;
import org.ow2.easywsdl.schema.impl.SimpleTypeImpl;
import org.ow2.easywsdl.wsdl.WSDLFactory;
import org.ow2.easywsdl.wsdl.api.BindingOperation;
import org.ow2.easywsdl.wsdl.api.Description;
import org.ow2.easywsdl.wsdl.api.Endpoint;
import org.ow2.easywsdl.wsdl.api.Part;
import org.ow2.easywsdl.wsdl.api.Service;
import org.xml.sax.InputSource;


/**
 * @author Chenchen Cheng
 *
 */
public class MatchingProcessEasywsdl {

	// get matched webservice
	private MatchedWebService getMatchedWebServiceSyntactic(Description d_o, Description d_i) throws Exception {

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

		for (BindingOperation o_o : p_o.getBinding().getBindingOperations()) {

			for (BindingOperation o_i : p_i.getBinding().getBindingOperations()) {
				MatchedOperation matchedOperation = new MatchedOperation();
				List<MatchedElement> mel = matchedOperation.getMatchedElements();

				matchedOperation.setInputOperationName(o_i.getQName().getLocalPart());
				matchedOperation.setOutputOperationName(o_o.getQName().getLocalPart());

				List<String> simpleElements_o = new ArrayList<String>();
				List<String> simpleElements_i = new ArrayList<String>();
				for (Part e_o : o_o.getOperation().getOutput().getParts()) {

					Type type = e_o.getType();

					if (type == null) {

						org.ow2.easywsdl.schema.api.Element element = e_o.getElement();
						simpleElements_o = getAllSimpleTypedElements(element, simpleElements_o);

					} else {
						String typeName = type.getQName().getLocalPart();
						CheckTypes checkT = new CheckTypes();
						if(checkT.isPrimitive(typeName)){
							simpleElements_o.add(e_o.getPartQName().getLocalPart());
						}
						
					}

				}
				for (Part e_i : o_i.getOperation().getInput().getParts()) {

					Type type = e_i.getType();

					if (type == null) {

						// System.out.println("There is no type
						// attribute of this part.");
						org.ow2.easywsdl.schema.api.Element element = e_i.getElement();
						simpleElements_i = getAllSimpleTypedElements(element, simpleElements_i);

					} else {
						String typeName = type.getQName().getLocalPart();
						CheckTypes checkT = new CheckTypes();
						if(checkT.isPrimitive(typeName)){
							simpleElements_i.add(e_i.getPartQName().getLocalPart());
						}
						
					}
				}

				if (simpleElements_o != null && simpleElements_o.size() > 0 && simpleElements_i != null
						&& simpleElements_i.size() > 0) {

					int size_o = simpleElements_o.size();
					int size_i = simpleElements_i.size();
					int num = size_o > size_i ? size_i : size_o;
					for (int k = 0; k < num; k++) {
						MatchedElement me = new MatchedElement();
						me.calculateScoreUsingEditDistance(simpleElements_o.get(k), simpleElements_i.get(k));
						if (me.getScore() > 0.8) {
							me.setOutputElement(simpleElements_o.get(k));
							me.setInputElement(simpleElements_i.get(k));
							mel.add(me);
						}
					}
				}

				if (!mel.isEmpty()) {

					matchedOperation.calculateOpScore(mel);
					mol.add(matchedOperation);

				}

			}
		}
		if (!mol.isEmpty()) {

			matchedWebService.calculateWSScore(mol);
			matchedWebService.setMatchedOperations(mol);
		}

		return matchedWebService;

	}



	public void wsdlParserSyntactic(String url) throws Exception {

		MatchedWebServiceList matchedWebServiceList = new MatchedWebServiceList();
		List<MatchedWebService> mwsl = matchedWebServiceList.getMatchedWebServices();
		MatchedWebService matchedWebService = new MatchedWebService();

		// getting all the WSDL files

		File folder = new File(url);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			FileInputStream outPutWs = new FileInputStream(listOfFiles[i]);
			Description d_o;
			try {// file not found problem
				d_o = WSDLFactory.newInstance().newWSDLReader().read(new InputSource(outPutWs));

			} catch (SchemaException e) {
				continue;
			}

			for (int j = 0; j < listOfFiles.length; j++) {
				if (j != i) {
					FileInputStream inPutWs = new FileInputStream(listOfFiles[j]);
					Description d_i;
					try {
						d_i = WSDLFactory.newInstance().newWSDLReader().read(new InputSource(inPutWs));

					} catch (SchemaException e) {
						continue;
					}

					matchedWebService = getMatchedWebServiceSyntactic(d_o, d_i);

					if (!matchedWebService.getMatchedOperations().isEmpty()) {
						mwsl.add(matchedWebService);
					}

				}

			}
		}
		matchedWebServiceList.setMatchedWebServices(mwsl);
		createOutput(matchedWebServiceList);
	}

	

	private List<String> getAllSimpleTypedElements(Element el, List<String> result) {

		if (result == null) {

			result = new ArrayList<>();

		}

		// System.out.println("result"+ result);
		if (null == el) {
			return result;
		}

		Type type = el.getType();

		if (type instanceof SimpleTypeImpl) {

			// System.out.println("method:simple type"+ type);
			
			String typeName = type.getQName().getLocalPart();
			CheckTypes checkT = new CheckTypes();
			if(checkT.isPrimitive(typeName)){
				result.add(el.getQName().getLocalPart());
			}
			
			// System.out.println("final result"+ result);

		} else if (type instanceof ComplexTypeImpl) {

			// System.out.println("method:type"+ type);

			// it's a complex type => must have a sequence

			Sequence seq = ((ComplexTypeImpl) type).getSequence();

			// though, some of them did not, probably a bug in the schema

			if (seq != null) {

				for (Element innerEl : seq.getElements()) {

					getAllSimpleTypedElements(innerEl, result);

				}

			}

		}
		return result;

	}

	private void createOutput(MatchedWebServiceList mwsl) {
		JAXBContext context;
		Marshaller m;
		try {
			context = JAXBContext.newInstance(MatchedWebServiceList.class);

			m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(mwsl, new File("src/output_syntactic.xml"));

		} catch (JAXBException ex) {
			Logger.getLogger(MatchedWebServiceList.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
