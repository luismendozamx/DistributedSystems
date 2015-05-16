/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBColumn;
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
public class UserCreateTable extends HttpServlet {

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
            
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            
            int numColumnas = Integer.parseInt(request.getParameter("numColumnas"));
            String tableName = request.getParameter("tableName");
            DBColumn[] columns = new DBColumn[20];
            String auxName, auxType;
            DBColumn tempColumn;
            
            for (int i = 1; i <= numColumnas; i++) {
                auxName = "campo-" + i;
                auxType = "tipo-" + i;
                tempColumn = new DBColumn(request.getParameter(auxName), request.getParameter(auxType));
                columns[i-1] = tempColumn;
            }
            
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
            
            int res = users.createTable(currentUser, tableName, columns, numColumnas);
            
            if(res == 0){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/profile.jsp");
                rd.forward(request, response);
            }else{
                request.setAttribute("error", "se generó un error al guardar la tabla");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/create.jsp");
                rd.forward(request, response);
            }
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
