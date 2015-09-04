import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

@WebServlet(name="ParanormalPhenomenaServlet", urlPatterns={"/ParanormalPhenomenaServlet"})
public class ParanormalPhenomenaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*Student: Dagnija KAulina-Brante
    *Student number: 13114310
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)//request-information coming from browser, response- information send back
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");// sets the content type for the response, send back before the actual page, so that browser knows what to do
        try (PrintWriter out = response.getWriter()) {//creates object so that we can add information to the response
            // Identify Sources
            String path = getServletContext().getRealPath("/WEB-INF/");//path
            String XMLFileName = path + "/PhenomenaXML.xml";//xml
            String XSLFileName = path + "/PhenomenaXSL.xsl";//xsl

            StreamSource XMLSource = new StreamSource(XMLFileName);
            StreamSource XSLSource = new StreamSource(XSLFileName);
            // Identify Result
            StreamResult homeOutput = new StreamResult(out);

            // Create TransformerFactory
            TransformerFactory xFactory = TransformerFactory.newInstance();

            // Create Transformer
            Transformer optimusPrime = xFactory.newTransformer(XSLSource);

            // Apply transform
            optimusPrime.transform(XMLSource, homeOutput);
        }
        catch (TransformerConfigurationException ex) {

            System.out.println("Encountered TransformerConfiguration Error: " + ex.getMessage());
            
        } catch (TransformerException ex) {

            System.out.println("Encountered Transformer Error: " + ex.getMessage());

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

