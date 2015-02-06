/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luismendoza
 */
public class SignZodiac extends HttpServlet {

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
            int dia = Integer.valueOf(request.getParameter("dia"));
            int mes = Integer.valueOf(request.getParameter("mes"));
            int anio = Integer.valueOf(request.getParameter("anio"));
                       
            String signo = "";
            
            switch(mes){
                case 1:
                    if(dia <= 20){
                        signo = "Capricornio";
                    }else{
                        signo = "Acuario";
                    }
                    break;
                case 2:
                    if(dia <= 19){
                        signo = "Acuario";
                    }else{
                        signo = "Piscis";
                    }
                    break;
                case 3:
                    if(dia <= 20){
                        signo = "Piscis";
                    }else{
                        signo = "Aries";
                    }
                    break;
                case 4:
                    if(dia <= 20){
                        signo = "Aries";
                    }else{
                        signo = "Tauro";
                    }
                    break;
                case 5:
                    if(dia <= 21){
                        signo = "Tauro";
                    }else{
                        signo = "Géminis";
                    }
                    break;
                case 6:
                    if(dia <= 21){
                        signo = "Géminis";
                    }else{
                        signo = "Cáncer";
                    }
                    break;
                case 7:
                    if(dia <= 22){
                        signo = "Cáncer";
                    }else{
                        signo = "Leo";
                    }
                    break;
                case 8:
                    if(dia <= 22){
                        signo = "Leo";
                    }else{
                        signo = "Virgo";
                    }
                    break;
                case 9:
                    if(dia <= 22){
                        signo = "Virgo";
                    }else{
                        signo = "Libra";
                    }
                    break;
                case 10:
                    if(dia <= 22){
                        signo = "Libra";
                    }else{
                        signo = "Escorpio";
                    }
                    break;
                case 11:
                    if(dia <= 22){
                        signo = "Escorpio";
                    }else{
                        signo = "Sagitario";
                    }
                    break;
                case 12:
                    if(dia <= 21){
                        signo = "Sagitario";
                    }else{
                        signo = "Capricornio";
                    }
                    break;
            }
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignZodiac</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Zodiaco</h1>");
            out.println("<p>Tu signo es: " + signo + "</p>");
            out.println("<form action=\"CompatibleSigns\">");
            out.println("<input type=\"hidden\" name=\"signo\" value='" + signo + "' />");
            out.println("<input type=\"submit\" value=\"Ver Signos Compatibles\" />");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            String back = "/index.jsp";
            request.setAttribute("error", "Error en la forma");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(back);
            dispatcher.forward(request, response);
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
