/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luismendoza
 */
@WebServlet(name = "ViewTable", urlPatterns = {"/ViewTable"})
public class ViewTable extends HttpServlet {

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
            int rows, cols;
            HttpSession session = request.getSession();
            
            User currentUser = (User) session.getAttribute("currentUser");
            
            // if no user redirect to login
            if(currentUser == null){
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
                rd.forward(request, response);
            }
            
            String tableName = request.getParameter("userTables");
            
            if(tableName==null){
                tableName = (String)session.getAttribute("tableName");
            }else{
                session.setAttribute("tableName", tableName);
            }
            
            // Recover data if necessary
            String action = request.getParameter("action");
            String prompt = null;
            int index = (Integer)session.getAttribute("index");
            Object[][] tableData = (Object[][])session.getAttribute("tableData");
            String[] columnNames = (String[])session.getAttribute("columnNames");
            Integer[] columnTypes = (Integer[])session.getAttribute("columnTypes");
            
            if(tableData==null || modificationNeeded(action)){
                try{
                    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + currentUser.getDatabase(), currentUser.getUsername(), currentUser.getPassword());
                    Statement st1 = con.createStatement();

                    // Get query that will be used to create tableData
                    ResultSet rs1 = st1.executeQuery("select * from root." + tableName);
                    ResultSetMetaData rsmd = rs1.getMetaData();
                    cols = rsmd.getColumnCount();
                    
                    // Apply any modifications if necessary
                    if(modificationNeeded(action)){
                        Statement st2 = con.createStatement();
                        StringBuilder sb = new StringBuilder();
                        if(action.equals("Eliminar")){
                            // Delete
                            sb.append("delete from root.").append(tableName).append(" ");
                            sb.append("where ");
                            // Assuming that first column is primary key for now
                            sb.append(columnNames[0]);
                            if(columnTypes[0]==Types.VARCHAR){
                                sb.append("='").append(tableData[index][0]).append("'");
                            }else{
                                sb.append("=").append(tableData[index][0]);
                            }
                            index--;
                        }else{
                            // Save
                            sb.append("insert into root.").append(tableName).append(" ");
                            sb.append("values (");
                            for(int j=0;j<cols;j++){
                                if(columnTypes[j]==Types.VARCHAR){
                                    sb.append("'").append(request.getParameter("f" + j)).append("'");
                                }else{
                                    sb.append(request.getParameter("f" + j));
                                }
                                if(j+1<cols){
                                    sb.append(",");
                                }
                            }
                            sb.append(")");
                        }
                        st2.executeUpdate(sb.toString());
                        con.commit();
                    }
                    
                    // Make a row count
                    Statement st3 = con.createStatement();
                    ResultSet rs3 = st3.executeQuery("select count(*) from root." + tableName);
                    rs3.next();
                    rows = rs3.getInt(1);

                    // If rows>0?
                    // Get data and column names from table
                    rs1 = st1.executeQuery("select * from root." + tableName);
                    tableData = new Object[rows][cols];
                    for(int i=0;i<rows;i++){
                        rs1.next();
                        for(int j=0;j<cols;j++){
                            tableData[i][j] = rs1.getObject(j+1);
                        }
                    }
                    session.setAttribute("tableData", tableData);
                    columnNames = new String[cols];
                    for(int j=0;j<cols;j++){
                        columnNames[j] = rsmd.getColumnName(j+1);
                    }
                    session.setAttribute("columnNames", columnNames);
                    columnTypes = new Integer[cols];
                    for(int j=0;j<cols;j++){
                        columnTypes[j] = rsmd.getColumnType(j+1);
                    }
                    session.setAttribute("columnTypes", columnTypes);
                    con.close();
                }catch(SQLException sqle){
                    prompt = sqle.toString();
                }
            }
            rows = tableData.length;
            cols = columnTypes.length;
            
            if(action==null || action.equals("|<")){
                index = 0;
            }else{
                if(action.equals("<")){
                    index--;
                }else{
                    if(action.equals(">")){
                        index++;
                    }else{
                        if(action.equals(">|")){
                            index = rows-1;
                        }else{
                            if(action.equals("Insertar")){
                                // Insert
                                index = rows;
                            }else{
                                if(action.equals("Cancelar")){
                                    // Cancel
                                    index = 0;
                                }else{
                                    // Delete or Save
                                    // This was already processed
                                }
                            }
                        }
                    }
                }
            }
            session.setAttribute("index", index);
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Tabla: " + currentUser.getDatabase() + "-" + tableName +  "</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='Logout'>Log Out</a>");
            out.println("<a href='profile.jsp'>Mi Perfil</a><hr>");
            out.println("<h2>Datos de: " +  tableName + "</h2>");
            if(prompt!=null){
                out.println(prompt);
            }
            out.println("<form action='ViewTable' method='POST'>");
            // Show tuple
            if(index<rows){
                // Fields with values
                for(int j=0;j<cols;j++){
                    out.println(getFieldString(columnNames[j],tableData[index][j]));
                }
            }else{
                // Fields with text inputs
                for(int j=0;j<cols;j++){
                    out.println(getFieldString(columnNames[j],j));
                }
            }
            
            out.println(getButtonsString(index, rows));
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
        }
        finally {
            out.close();
        }
    }
    
    private boolean modificationNeeded(String action){
        return action!=null && (action.equals("Eliminar") || action.equals("Guardar"));
    }
    
    private String getFieldString(String column, Object value){
        StringBuilder sb = new StringBuilder("<br/>");
            sb.append(column).append(": ");
            sb.append(String.valueOf(value));
        return sb.toString();
    }
    
    private String getFieldString(String column, int j){
        StringBuilder sb = new StringBuilder("<br/>");
            sb.append(column).append(": ");
            sb.append("<input ");
            sb.append("type='text' ");
            sb.append("name='f").append(j).append("' ");
            sb.append("/>");
        return sb.toString();
    }
    
    private String getButtonsString(int i, int n){
        StringBuilder sb = new StringBuilder("<br/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='|<' ");
        if(i==0 || i==n){
            sb.append("disabled ");
        }
        sb.append("/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='<' ");
        if(i==0 || i==n){
            sb.append("disabled ");
        }
        sb.append("/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='>' ");
        if(i>=n-1){
            sb.append("disabled ");
        }
        sb.append("/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='>|' ");
        if(i>=n-1){
            sb.append("disabled ");
        }
        sb.append("/>");
        
        sb.append("<br/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        if(i==n){
            sb.append("value='Cancelar' ");
        }else{
            sb.append("value='Insertar' ");
        }
        sb.append("/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='Eliminar' ");
        if(i==n){
            sb.append("disabled ");
        }
        sb.append("/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='Guardar' ");
        if(i!=n){
            sb.append("disabled ");
        }
        sb.append("/>");
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
