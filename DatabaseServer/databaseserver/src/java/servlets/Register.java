package servlets;

import util.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {

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
            String email = request.getParameter("username");
            s.setAttribute("rUsername", email);
            String password = request.getParameter("password");
            s.setAttribute("rPassword", password);
            String database = request.getParameter("database");
            s.setAttribute("rDatabase", database);
            
            UserManager um = (UserManager)s.getAttribute("users");
            if(um==null){
                try{
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                }catch(ClassNotFoundException cnfe){
                    System.err.println(cnfe.toString());
                }
                um = new UserManager("jdbc:derby://localhost:1527/UserDB");
                s.setAttribute("users", um);
            }
            RequestDispatcher rd;
            if(database.equals("UserDB")){
                // Users cannot register with the user Database
                s.setAttribute("rPrompt", "<h2>The database name is already taken.</h2>"
                                        + "Please select another database name.");
                rd = getServletContext().getRequestDispatcher("/register.jsp");
                rd.include(request, response);
            }else{
                int result = um.addUser(email, password, database);
                if(result>0){
                    if(result==1){
                        // Username is already taken
                        s.setAttribute("rPrompt", "<h2>The username is already taken.</h2>"
                                                + "Please select another username.");
                    }else{
                        // Database is already taken
                        s.setAttribute("rPrompt", "<h2>The database name is already taken.</h2>"
                                                + "Please select another database name.");
                    }
                    rd = getServletContext().getRequestDispatcher("/register.jsp");
                    rd.include(request, response);
                }else{
                    um.save(); // Save new user
                    try{
                        // create the DB for the selected user
                        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + database + ";create=true;", email, password); 
                        con.commit();
                        con.close();
                        s.setAttribute("username", email);
                        s.setAttribute("password", password);
                        s.setAttribute("database", database);
                        rd = getServletContext().getRequestDispatcher("/Profile");
                        rd.forward(request, response);
                    }catch(SQLException sqle){
                        System.err.println(sqle.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            out.close();
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
