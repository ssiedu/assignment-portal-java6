import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveFaculty extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        //read,process,response
        String s1=request.getParameter("fcode");
        String s2=request.getParameter("fname");
        String s3=request.getParameter("address");
        String s4=request.getParameter("mobile");
        String s5=request.getParameter("email");
        String s6=request.getParameter("qualification");
        String sql="insert into faculty values(?,'ssi',?,?,?,?,?)";
        //code,pwd,name,adr,mobile,email,qual,email
        out.println("<html><body>");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ssidata","root","root");
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, s1);
            ps.setString(2, s2);
            ps.setString(3, s3);
            ps.setString(4, s4);
            ps.setString(5, s6);
            ps.setString(6, s5);
            ps.executeUpdate();
            con.close();
            out.println("<h3>Account Created Successfully</h3>");
            out.println("<h4><a href=facultycreation.jsp>Create-More</a></h4>");
            out.println("<h4><a href=adminpage.jsp>Admin-Page</a></h4>");            
        }catch(Exception e){
            out.println("<h3>Cant Create Account</h3>");
        }
        out.println("</body></html>");
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
