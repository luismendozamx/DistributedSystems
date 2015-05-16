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
public class UserRegister extends HttpServlet {

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
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String database = request.getParameter("database");
            HttpSession session = request.getSession();
            
            User currentUser = new User(username, password, database);
            DBUserManager users = (DBUserManager) session.getAttribute("users");
            
            if (users == null){
                try{
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                }catch(ClassNotFoundException cnfe){
                    System.err.println(cnfe.toString());
                }
                users = new DBUserManager("jdbc:derby://localhost:1527/UsersDBW");
                session.setAttribute("users", users);
            }
            
            int res = users.registerUser(currentUser);
            
            RequestDispatcher rd;
            
            switch(res){
                case 0:
                    // Guardar usuario en sesión
                    session.setAttribute("currentUser", currentUser);
                    
                    // redirigir usuario a profile.
                    rd = getServletContext().getRequestDispatcher("/profile.jsp");
                    rd.include(request, response);
                    break;
                case 1:
                    // error, nombre de db ya existe
                    request.setAttribute("error", "base de datos ya existe");
                    rd = getServletContext().getRequestDispatcher("/register.jsp");
                    rd.include(request, response);
                    break;
                case 2:
                    // error, username ya existe
                    request.setAttribute("error", "username ya existe");
                    rd = getServletContext().getRequestDispatcher("/register.jsp");
                    rd.include(request, response);
                    break;
                case -1:
                    // error, no se pudo crear
                    request.setAttribute("error", "se generó un error al guardar el usuario");
                    rd = getServletContext().getRequestDispatcher("/register.jsp");
                    rd.forward(request, response);
                    break;
            }
            
        }catch(Exception e){
            
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
