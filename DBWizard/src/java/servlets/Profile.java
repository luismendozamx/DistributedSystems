/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBUserManager;
import database.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luismendoza
 */
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Profile</title>");
            
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            
            // if no user redirect to login
            if(currentUser == null){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
            
            // Print top part of the page
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='Logout'>Log Out</a>");
            out.println("<a href='create.jsp'>Crea Tabla</a>");
            out.println("<hr>");
            out.println("<h1>Perfil de " + currentUser.getUsername() + "</h1>");
            
            // Get dbmanager
            DBUserManager users = (DBUserManager) session.getAttribute("users");
            
            if (users == null){
                try{
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                }catch(ClassNotFoundException cnfe){
                    System.err.println(cnfe.toString());
                }
                users = new DBUserManager("jdbc:derby://localhost:1527/UsersDBW");
                session.setAttribute("users", users);
            }else{
                users.reloadUserList();
            }
            
            ArrayList<String> tables = users.getUserTables(currentUser);
            
            if(tables == null || tables.size() == 0){
                out.println("<p>Aún no tienes tablas, <a href='create.jsp'>crea una nueva</a></p>");
            }else{
                out.println("<p>Tablas registradas:</p>");
                out.println("<form action='ViewTable' method=POST>");
                out.println("<select name='userTables'>");
                int i = 0;
                while(i < tables.size()){
                    out.println("<option value='" + tables.get(i) + "'>" + tables.get(i) + "</option>");
                    i++;
                }
                out.println("</select>");
                out.println("<input type='submit' value='Ver tabla' />");
                out.println("<input type='submit' value='Eliminar tabla' onclick='' />");
                out.println("</form>");
                out.println("<p><a href='create.jsp'>Crea tabla nueva</a></p>");
            }
            
            
            out.println("</body>");
            out.println("</html>");
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
