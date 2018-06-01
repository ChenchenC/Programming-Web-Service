/**
 * 
 */
package method;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Chenchen Cheng
 *
 */
public class XsltTemplateProcessor {
 /*   public static void main(String args[]) {
        Source xml = new StreamSource(new File("src/xml/transcript.xml"));
        Source xslt = new StreamSource("src/xml/TranscriptTemplate.xsl");
        convertXMLToHTML(xml, xslt);
    }*/

    public void convertXMLToHTML(String inputFile, String xsltFile, String outputFile) {
        StringWriter sw = new StringWriter();
        Source xml = new StreamSource(new File(inputFile));
        Source xslt = new StreamSource(xsltFile);

        try {

            FileWriter fw = new FileWriter(outputFile);
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer trasform = tFactory.newTransformer(xslt);
            trasform.transform(xml, new StreamResult(sw));
            fw.write(sw.toString());
            fw.close();

            System.out.println("Result generated successfully.");

        } catch (IOException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }
}
