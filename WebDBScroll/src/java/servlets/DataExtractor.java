/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luismendoza
 */
public class DataExtractor extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataExtractor</title>");            
            out.println("</head>");
            out.println("<body>");
            try {
                HttpSession mySession = request.getSession();
                String session = (String) mySession.getAttribute("session");
                
                if(session != null && session.equals("valid")){
                    // Ya hay sesión y debe haber algo en el result set
                    int cont = (Integer) mySession.getAttribute("cont");
                    
                    if (request.getAttribute("click").equals("next")){
                        cont++;
                        mySession.setAttribute("cont", cont);
                    }else{
                        cont--;
                        mySession.setAttribute("cont", cont);
                    }
                    
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/scroll.jsp");
                    dispatcher.forward(request, response);
                    
                }else{
                    // No había nada guardado
                    Class.forName("org.apache.derby.jdbc.ClientDriver");
                
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/dev",
                                                                 "root",
                                                                 "root");

                    Statement query = con.createStatement();
                    ResultSet rs = query.executeQuery("SELECT * FROM CUSTOMER");
                    
                    ArrayList<Dato> lista = new ArrayList<Dato>();
                    
                    Dato d = null;
                    
                    while(rs.next()){
                        d = new Dato(rs.getInt("ID"),rs.getString("NAME"));
                        lista.add(d);
                    }
                    
                    mySession.setAttribute("session", "valid");
                    mySession.setAttribute("data", lista);
                    mySession.setAttribute("cont", 0);
                    
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/scroll.jsp");
                    dispatcher.forward(request, response);
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
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
