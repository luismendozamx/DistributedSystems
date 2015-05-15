package servlets;

import util.UserManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

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
            if(s.getAttribute("username")!=null){
                RequestDispatcher d = this.getServletContext().getRequestDispatcher("/Profile");
                d.forward(request, response);
            }else{
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                if(um.isValid(username, password)){
                    s.setAttribute("username", username);
                    s.setAttribute("database", um.getUserDatabase());
                    s.setAttribute("password", password);
                    RequestDispatcher d = this.getServletContext().getRequestDispatcher("/Profile");
                    d.forward(request, response);
                }else{
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Incorrect Login</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h2>The username or password is incorrect</h2>");
                    RequestDispatcher d = this.getServletContext().getRequestDispatcher("/login.jsp");
                    d.include(request, response);
                    out.println("</body>");
                    out.println("</html>");
                }
            }
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
