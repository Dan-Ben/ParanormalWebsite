/* ParanormalHouseNext.java
 * Student: Dagnija Kaulina-Brante
 * Student number: 13114310
 */
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

@WebServlet(name="ParanormalHouseNext", urlPatterns={"/ParanormalHouseNext"})
public class ParanormalHouseNext extends HttpServlet {
/*Student: Dagnija KAulina-Brante
*Student number: 13114310
 */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String URI = "xmldb:exist://localhost:8088/exist/xmlrpc";
        String driver = "org.exist.xmldb.DatabaseImpl";
        XMLResource res = null;
        Node resNode = null;
        Document doc = null;

        String path = getServletContext().getRealPath("/WEB-INF/");
        String XSLFileName = path + "/HauntedHouseFull.xsl";//looks for xsl file

        String house;
        String owner_Now;
        String date;
        String timeOpen;
        try {

            house = request.getParameter("title");
            owner_Now = request.getParameter("ownerNow");
            date = request.getParameter("date");
            timeOpen = request.getParameter("time");
  
            if (house == null) house="";
            if (owner_Now == null) owner_Now="";
            if (date == null) date="";
            if (timeOpen == null) timeOpen="";
        } 
        catch (Exception e) {
            house = "";
            owner_Now = "";
            date = "";
            timeOpen="";
        }
        try {
         
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);
  
            Collection col = DatabaseManager.getCollection(URI + "/db/Paranormal");
        
            XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
            XQueryService another = (XQueryService) col.getService("XQueryService", "1.0");
            service.setProperty("indent", "yes");
            another.setProperty("indent", "yes");
            String queryString = "";
          
            if (!(owner_Now.equals(""))) {
                service.declareVariable("currentOwner", "");
                queryString = "for $currentOwner in //house/title[text()='" +  house + "']/../ownerNow " +
                              "return update replace $currentOwner with <ownerNow>" + owner_Now + "</ownerNow>";
                service.query(queryString);
            }
             if (!(date.equals(""))) {
                service.declareVariable("date", "");
                queryString = "for $date in //house/title[text()='" +  house + "']/../openForPublic/code/date " +
                              "return update replace $date with <date>" + date + "</date>";
                service.query(queryString);
            }
            if (!(timeOpen.equals(""))) {
                service.declareVariable("OpeningTime", "");
                queryString = "for $OpeningTime in //house/title[text()='" +  house + "']/../openForPublic/code/time " +
                              "return update replace $OpeningTime with <time>" + timeOpen + "</time>";
                another.query(queryString);
            }
            col.setProperty(OutputKeys.INDENT, "no");
           
            res = (XMLResource)col.getResource("HauntedHouse");
            resNode = res.getContentAsDOM();
            doc = (Document) resNode;

        }
        catch (Exception e) {
            System.err.println("Error Document: "+e.getMessage());
        }
        
        DOMSource origDocSource = new DOMSource(doc);

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            StreamSource stylesheet = new StreamSource(XSLFileName);
            Transformer transformer = transformerFactory.newTransformer(stylesheet);
            
            NodeList houseNodeList = doc.getElementsByTagName("title");
          
            int num_houses = houseNodeList.getLength();
            String prev_house;
            String next_house;
        
            for (int i=0;i<num_houses; i++){
                Node houseNode = houseNodeList.item(i);
         
                NodeList houseNodeListChildren = houseNode.getChildNodes();
                Node houseTextNode = houseNodeListChildren.item(0);
                String houseValue = houseTextNode.getNodeValue();
              
                 if (house.equals(houseValue)) {

                    if (i!=0) {
                     
                        prev_house=houseNodeList.item(i-1).getChildNodes().item(0).getNodeValue();
                    } else {
                   
                        prev_house=houseNodeList.item(num_houses-1).getChildNodes().item(0).getNodeValue();
                    }

                    if (i!=(num_houses-1)) {
                      
                        next_house=houseNodeList.item(i+1).getChildNodes().item(0).getNodeValue();
                    } else {
                 
                        next_house=houseNodeList.item(0).getChildNodes().item(0).getNodeValue();
                    }
               
                    transformer.setParameter("vhouseTitle", houseValue);
                    transformer.setParameter("vNextName", prev_house);
                    transformer.setParameter("vPrevName", next_house);
                  
                    transformer.transform(origDocSource,new StreamResult(out));
                }
            }
        } 
        catch (Exception e) {
            out.println("Exception transformation :"+e.getMessage());
            e.printStackTrace(out);
        } 
        finally {
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
