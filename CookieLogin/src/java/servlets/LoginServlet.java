/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luismendoza
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            // Include links
            RequestDispatcher dis = getServletContext().getRequestDispatcher("/link.html");
            dis.include(request, response);
            
            // Look for cookies to see if user is logged in
            Cookie myCookies[] = request.getCookies();
            String cookieName = "";
            String cookieValue = "";
            Boolean flag = false;
            
            for (int i = 0; i < myCookies.length; i++) {
                cookieName = myCookies[i].getName();
                cookieValue = myCookies[i].getValue();
                if(cookieName.equals("session") && cookieValue.equals("valid") ){
                    flag = true;
                }
            }
            
            if(!flag){
                // No había sesión
                String name = request.getParameter("name");
                String password = request.getParameter("password");

                if(password.equals("123456") && !name.equals("")){
                    out.println("<p>Success!</p>");
                    out.println("<p>Welcome "+ name +"</p>");
                    Cookie sessionCookie = new Cookie("session", "valid");
                    response.addCookie(sessionCookie);
                    Cookie nameCookie = new Cookie("username", name);
                    response.addCookie(nameCookie);
                }else{
                    out.println("<p>Error, wrong username or password</p>");
                    dis = getServletContext().getRequestDispatcher("/login.html");
                    dis.include(request, response);
                }
            }else{
                out.println("<p>You are already logged in</p>");
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
