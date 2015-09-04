/*
 * Daniel B
 * x13341086
 */

import java.io.File;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import java.io.IOException;
import java.io.PrintWriter;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
import javax.xml.transform.OutputKeys;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import static sun.audio.AudioPlayer.player;



@WebServlet(name="ParanormalEntitiesNext", urlPatterns={"/ParanormalEntitiesNext"})
public class ParanormalEntitiesNext extends HttpServlet {
  
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String URI = "xmldb:exist://localhost:8088/exist/xmlrpc";
        String driver = "org.exist.xmldb.DatabaseImpl";


        XMLResource res = null;
        Node resNode = null;
        Document doc = null;

//XML FROM EXIST DATABASE, ONLY LINK XSL HERE
        String path = getServletContext().getRealPath("/WEB-INF/");
        String XSLFileName = path + "/EntitiesFULLXSL.xsl";
            
//THE STRING VALUE YOU WILL USE TO IDENTIFY THE PHENOMENA SUCH AS NAME, AND ANY VALUES YOU WANT TO GIVE AN UPDATE FUNCTION
        String entity;
        String sightings;
        try {
            entity = request.getParameter("name");
            sightings = request.getParameter("sightings");
            
            if (entity == null) entity="";
            if (sightings == null) sightings="";
        } catch (Exception e) {
            entity = "";
            sightings = "";
         
        }
            
   
        //EXIST DATABASE COMMUNICATION
        try {
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);

            // get the collection
            //XPATH SEARCHES FOR THE NAME OF THE ENTITY, ONCE IT FINDS NAME IT MOVES BACK UP WITH XPATH AND BACK DOWN TO THE SIGHTINGS TAG SO IT CAN UPDATE IT
            Collection col = DatabaseManager.getCollection(URI + "/db/Paranormal");

            XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
            XQueryService another = (XQueryService) col.getService("XQueryService", "1.0");
            service.setProperty("indent", "yes");
            another.setProperty("indent", "yes");
            String queryString = "";
            if (!(sightings.equals(""))) {
                service.declareVariable("sightings", "");
                queryString = "for $sightings in //Entity/name[text()='" +  entity + "']/../information/sightings " +
                              "return update replace $sightings with <sightings>" + sightings + "</sightings>";
                service.query(queryString);
            }
            
            col.setProperty(OutputKeys.INDENT, "no");
            res = (XMLResource)col.getResource("EntitiesXML");

            resNode = res.getContentAsDOM();

            doc = (Document) resNode;

        }catch (Exception e) {
            System.err.println("Error Document: "+e.getMessage());
        }
        DOMSource origDocSource = new DOMSource(doc);

//LOTS TO REFACTOR HERE
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource stylesheet = new StreamSource(XSLFileName);

            Transformer transformer = transformerFactory.newTransformer(stylesheet);

            NodeList entityNodeList = doc.getElementsByTagName("name");
            int num_entities = entityNodeList.getLength();
            String prev_entity;
            String next_entity;

            for (int i=0;i<num_entities; i++){

                Node entityNode = entityNodeList.item(i);

                NodeList entityNodeListChildren = entityNode.getChildNodes();
                Node entityTextNode = entityNodeListChildren.item(0);
                String entityValue = entityTextNode.getNodeValue();
                 if (entity.equals(entityValue)) {

                    if (i!=0) {
                        prev_entity=entityNodeList.item(i-1).getChildNodes().item(0).getNodeValue();
                    } else {
                        prev_entity=entityNodeList.item(num_entities-1).getChildNodes().item(0).getNodeValue();
                    }

                    if (i!=(num_entities-1)) {
                        next_entity=entityNodeList.item(i+1).getChildNodes().item(0).getNodeValue();
                    } else {
                        next_entity=entityNodeList.item(0).getChildNodes().item(0).getNodeValue();
                    }

                    transformer.setParameter("vName", entityValue);
                    transformer.setParameter("vPrev_entity", prev_entity);
                    transformer.setParameter("vNext_entity", next_entity);

                    transformer.transform(origDocSource,new StreamResult(out));
                }
            }
        } catch (Exception e) {
            out.println("Exception transformation :"+e.getMessage());
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
