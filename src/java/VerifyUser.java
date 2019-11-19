
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        //read the request
        String s1 = request.getParameter("email");
        String s2 = request.getParameter("password");
        String s3 = request.getParameter("usertype");
        //process the request
        if (s3.equals("admin")) {
            if (s1.equals("admin@gmail.com") && s2.equals("ssi")) {
                response.sendRedirect("adminpage.jsp");
            } else {
                out.println("<html><body>");
                out.println("<h4>Invalid Admin Account</h4>");
                out.println("<h5><a href=index.jsp>Try-Again</a></h5>");
                out.println("</body></html>");
            }

        } else if (s3.equals("faculty")) {
            String sql = "select * from faculty where email=? and password=?";
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssidata", "root", "root");
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, s1);
                ps.setString(2, s2);
                ResultSet rs = ps.executeQuery();
                boolean res = rs.next();
                if (res == true) {
                    response.sendRedirect("facultypage.jsp");
                } else {
                    out.println("<html><body>");
                    out.println("<h4>Invalid Faculty Account</h4>");
                    out.println("<h5><a href=index.jsp>Try-Again</a></h5>");
                    out.println("</body></html>");
                }
                con.close();
            } catch (Exception e) {
                out.println(e);
            }

        } else if (s3.equals("student")) {
            //jdbc-code for student authentication
            response.sendRedirect("studentpage.jsp");
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
