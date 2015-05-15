package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Profile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        try {
       
            HttpSession s = request.getSession();
            // Erase variables from ScrollTable
            s.setAttribute("tableData", null);
            s.setAttribute("columnNames", null);
            s.setAttribute("columnTypes", null);
            s.setAttribute("index", 0);
            
            // Erase variables from CreateTable
            s.setAttribute("fields", null);
            s.setAttribute("nFields", 0);
            
            String username = (String)s.getAttribute("username");
            String database = (String)s.getAttribute("database");
            
            ArrayList<String> tables = (ArrayList<String>)s.getAttribute("tables");
            if(tables==null){
                String password = (String)s.getAttribute("password");
                try{
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + database, username, password);
                    DatabaseMetaData dmd = con.getMetaData();
                    String[] tableTypes = new String[1];
                    tableTypes[0] = "TABLE";
                    ResultSet rs = dmd.getTables(null, null, null, tableTypes);
                    tables = new ArrayList<String>();
                    s.setAttribute("tables", tables);
                    while(rs.next()){
                        tables.add(rs.getString("TABLE_NAME").toUpperCase());
                    }
                    con.close();
                }catch(SQLException sqle){
                    System.err.println(sqle.toString());
                }
            }
            out.println("<html>");
            out.println("<head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<title>Dropbase Profile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='CreateTable'>Create a new table</a> <a href='Logout'>Logout</a>");
            out.println("<hr>");
            out.println("<h1>Welcome to your profile, " + username + "!</h1>");
            out.println("The name of your database is " + database + ".");
            out.println("<br/>");
            if(tables.size()>0){
                out.println("<form action='ScrollTable' method='POST'>");
                out.println(getSelectString(tables.iterator()));
                out.println("<input type='submit' value='View table data' />");
                out.println("</form>");
            }else{
                out.println("You have not created any tables yet.");
            }
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e){
            out.println("<p>" + e.getMessage() + "</p>");
        } finally {
            out.close();
        }
    }
    
    private String getSelectString(Iterator<String> it){
        StringBuilder sb = new StringBuilder();
        sb.append("<select name='selectTable'>");
        while(it.hasNext()){
            sb.append("<option>").append(it.next()).append("</option>");
        }
        sb.append("</select>");
        return sb.toString();
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
