import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectSearchResult extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String subject=request.getParameter("subject");
        String sql="SELECT * FROM quebank where subject=?";
        try{
            Connection con=mypkg.Data.connect();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, subject);
            ResultSet rs=ps.executeQuery();
            out.println("<html>");
            out.println("<body>");
            out.println("<h3>List-of-Questions-For-"+subject+"</h3>");
            out.println("<table border=2>");
            out.println("<tr>");
            out.println("<th>Code</th>");
            out.println("<th>Que</th>");
            out.println("<th>Date</th>");
            out.println("<th>FCode</th>");
            out.println("<tr>");
            while(rs.next()){
                String s1=rs.getString("qcode");
                String s2=rs.getString("que");
                String s3=rs.getString("sdate");
                String s4=rs.getString("fcode");
                out.println("<tr>");
                out.println("<td>"+s1+"</td>");
                out.println("<td>"+s2+"</td>");
                out.println("<td>"+s3+"</td>");
                out.println("<td>"+s4+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<hr>");
            out.println("<h4><a href=subjectsearch.jsp>Search-More</a></h3>");
            out.println("<h4><a href=studentpage.jsp>Student-Home</a></h3>");
            out.println("</body>");
            out.println("</html>");
            con.close();
        }catch(Exception e){
            out.println(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
