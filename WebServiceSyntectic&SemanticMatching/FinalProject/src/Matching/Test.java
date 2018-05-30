/**
 * 
 */
package Matching;

/**
 * @author Chenchen Cheng
 *
 */
public class Test {
	public static void main(String[] args){
		String url1 = "src/WSDLs";
		String url2 = "src/SAWSDLs";
		
		try {
			//syntacttic
			MatchingProcessEasywsdl mpE = new MatchingProcessEasywsdl();
			//semantic
			MatchingProcessSAWSDL mpESAWSDL = new MatchingProcessSAWSDL();
			mpE.wsdlParserSyntactic(url1);
			mpESAWSDL.wsdlParserSemantic(url2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
