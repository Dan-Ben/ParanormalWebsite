/*
 *Dagnija Kaulina-Brante
* 13114310
* for transforming main page
 */

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class TransformerMain{

    public static void main(String[] args) throws TransformerConfigurationException, TransformerException {
        
        /*Createing the Source XML Document**/
        String XMLFileName = "HauntedHouse.xml";
        StreamSource streamSource = new StreamSource(XMLFileName);
 
        /*Creating the resulting HTML file that will stor the result of the transformation**/
        String OutputFileName = "main.html";
        StreamResult result = new StreamResult(OutputFileName);
        
        /*Create a TransformerFactory to do the work of transforming the XML to HTML**/
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        
        /*Determine the style sheet based on the processing instructions in the source document*/
        Source stylesheet = transformerFactory.getAssociatedStylesheet(streamSource, null, null, null);
        
        /*Create the transformer*/
        Transformer transformer = transformerFactory.newTransformer(stylesheet);
        
        /*Use the transform method of the transformer to transform the source into a result*/
        transformer.transform(streamSource, result);
                        
    }
}
