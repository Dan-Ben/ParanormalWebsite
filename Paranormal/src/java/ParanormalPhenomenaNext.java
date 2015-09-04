/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FL
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
   @WebServlet(name="ParanormalPhenomenaNext", urlPatterns={"/ParanormalPhenomenaNext"})

  
 
public class ParanormalPhenomenaNext extends HttpServlet {
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // File Info
            String path = getServletContext().getRealPath("/WEB-INF/");
            String XMLFileName = path + "/PhenomenaXML.xml";
            String XSLFileName = path + "/PhenomenaFull.xsl";

            // Get Query String Param
            String phenomenaTitle = request.getParameter("name");

            // Create DOM object
            File docFile = new File(XMLFileName);

            Document doc = null;
            try {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(docFile);
            }catch (Exception e) {
                System.err.println("Error Document: "+e.getMessage());
            }

            // Identify Sources

            DOMSource XMLSource = new DOMSource(doc);
            StreamSource XSLSource = new StreamSource(XSLFileName);

            // Identify Result
            StreamResult homeOutput = new StreamResult(out);

            // Create TransformerFactory
            TransformerFactory xFactory = TransformerFactory.newInstance();

            // Create Transformer
            Transformer optimusPrime = xFactory.newTransformer(XSLSource);

            // Get Navigation Info
            NodeList phenomenaNodeList = doc.getElementsByTagName("Phenomena");
            int num_phenomena = phenomenaNodeList.getLength();
            String prev_name;
            String next_name;

            for (int i=0;i<num_phenomena; i++){

                Node eventNode = phenomenaNodeList.item(i);

                NodeList phenomenaNodeListChildren = eventNode.getChildNodes();
                Node phenomenaNameNode = phenomenaNodeListChildren.item(1);
                Node phenomenaTextNode = phenomenaNameNode.getChildNodes().item(0);
                String phenomenaNameValue = phenomenaTextNode.getNodeValue();
                 if (phenomenaTitle.equals(phenomenaNameValue)) {

                    if (i!=0) {
                        prev_name=phenomenaNodeList.item(i-1).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    } else {
                        prev_name=phenomenaNodeList.item(num_phenomena-1).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    }

                    if (i!=(num_phenomena-1)) {
                        next_name=phenomenaNodeList.item(i+1).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    } else {
                        next_name=phenomenaNodeList.item(0).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    }

                    // Apply transform
                    optimusPrime.setParameter("vPhenomenaTitle", phenomenaTitle);
                    optimusPrime.setParameter("vNextName", next_name);
                    optimusPrime.setParameter("vPrevName", prev_name);
                    optimusPrime.transform(XMLSource, homeOutput);

                 }
            }

        } catch (TransformerConfigurationException ex) {

            System.out.println("Encountered TransformerConfiguration Error: " + ex.getMessage());

        } catch (TransformerException ex) {

            System.out.println("Encountered Transformer Error: " + ex.getMessage());

        } finally { 
            out.close();
        }
    } 
        
        
    }

