package servlets;

import util.Field;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateTable extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            HttpSession s = request.getSession();
            String username = (String)s.getAttribute("username");
            String password = (String)s.getAttribute("password");
            String database = (String)s.getAttribute("database");
            ArrayList<String> tables = (ArrayList<String>)s.getAttribute("tables");
            if(tables==null){
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
            Field[] fields = (Field[])s.getAttribute("fields");
            int nFields = (Integer)s.getAttribute("nFields");
            if(fields==null){
                fields = new Field[10];
                s.setAttribute("fields", fields);
                nFields = 0;
                s.setAttribute("nFields", nFields);
            }
            String prompt = null;
            String action = request.getParameter("action");
            String table = request.getParameter("table");
            // Recover field parameters to keep track of table
            for(int i=0;i<nFields;i++){
                fields[i].setName(request.getParameter("fieldName" + i));
                fields[i].setType(request.getParameter("type" + i));
                if(request.getParameter("pk" + i)!=null){
                    fields[i].setPrimary(true);
                }
            }
            if(action!=null){
                if(action.equals("New Field")){
                    if(nFields==fields.length){
                        Field[] expansion = new Field[2*nFields];
                        for(int i=0;i<nFields;i++){
                            expansion[i] = fields[i];
                        }
                        fields = expansion;
                        s.setAttribute("fields", fields);
                    }
                    fields[nFields] = new Field();
                    if(nFields==0){
                        fields[nFields].setPrimary(true);
                    }
                    nFields++;
                    s.setAttribute("nFields", nFields);
                }else{
                    if(action.equals("Delete Field")){
                        if(nFields>0){
                            nFields--;
                            fields[nFields] = null;
                            s.setAttribute("nFields", nFields);
                        }else{
                            prompt = "There is no field to delete.";
                        }
                    }else{
                        // DEBUGGEAR
                        // Create table
                        // Does not validate pk for now
                        boolean validFields = nFields>0;
                        int i = 0, j;
                        while(i<nFields && validFields){
                            j = i + 1;
                            while(j<nFields && validFields){
                                validFields = !fields[i].getName().equalsIgnoreCase(fields[j].getName());
                                j++;
                            }
                            i++;
                        }
                        if(validFields){
                            StringBuilder sb = new StringBuilder("create table ");
                            StringBuilder pk = new StringBuilder("primary key(");
                            int pkSize = 0;
                            sb.append(table).append("(");
                            for(i=0;i<nFields;i++){
                                sb.append(fields[i].getName());
                                sb.append(fields[i].getType());
                                sb.append(fields[i].getNotNull());
                                sb.append(fields[i].getUnique());
                                sb.append(", ");
                                if(fields[i].isPrimary()){
                                    if(pkSize>0){
                                        pk.append(", ");
                                    }
                                    pk.append(fields[i].getName());
                                    pkSize++;
                                }
                            }
                            pk.append(")"); // Close primary keys

                            sb.append(pk);
                            sb.append(")"); // Close table

                            prompt = sb.toString();
                            
                            // Create table
                            // Forward to Profile
                            try{
                                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + database, username, password);
                                Statement st = con.createStatement();
                                st.executeUpdate(sb.toString());
                                con.commit();
                                con.close();
                                s.setAttribute("tables",null);
                                RequestDispatcher rd = getServletContext().getRequestDispatcher("/Profile");
                                rd.forward(request, response);
                            }catch(SQLException sqle){
                                prompt = sqle.toString();
                            }
                        }else{
                            if(nFields==0){
                                prompt = "There are no fields for this table.";
                            }else{
                                prompt = "Field names must be unique.";
                            }
                        }
                    }
                }
            }
            if(table!=null && tables.contains(table.toUpperCase())){
                if(prompt==null){
                    prompt = "The table name is already taken";
                }else{
                    prompt += "<br/>The table name is already taken";
                }
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + database +  "::Create Table</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<a href='Profile'>Cancel</a><hr>");
            if(prompt==null){
                out.println("Please design the new table");
            }else{
                out.println(prompt);
            }
            out.println("<form action='CreateTable' method='POST'>");
            out.println(getTableString(table));
            for(int i=0;i<nFields;i++){
                out.print(getFieldString(i, fields[i].getName(), fields[i].getType().trim(), fields[i].isPrimary()));
            }
            out.println(getButtonsString(nFields));
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    
    private String getTableString(String table){
        StringBuilder sb = new StringBuilder();
        sb.append("<br/>Table name: ");
        sb.append("<input ");
        sb.append("type='text' ");
        sb.append("name='table' ");
        sb.append("pattern='[A-Za-z][A-Za-z0-9]*' ");
        sb.append("title='The table name must start with a letter and can only contain letters and numbers.' ");
        if(table!=null){
            sb.append("value='").append(table).append("' ");
        }
        sb.append("/>");
        return sb.toString();
    }
        
    private String getFieldString(int index, String name, String type, boolean primary){
        StringBuilder sb = new StringBuilder();
        sb.append("<br/><b>Field ").append(index+1).append(":</b>");
        
        sb.append("Name: ");
        sb.append("<input ");
        sb.append("type='text' ");
        sb.append("name='fieldName").append(index).append("' ");
        sb.append("pattern='[A-Za-z][A-Za-z0-9][A-Za-z0-9]*' ");
        sb.append("title='The field name can only contain letters and numbers, must start with a letter and be at least 2 characters long.' ");
        if(!name.equals("")){
            sb.append("value='").append(name).append("' ");
        }
        sb.append("/> ");
        
        sb.append("Type: ");
        sb.append("<select ");
        sb.append("name='type").append(index).append("' ");
        sb.append(">");
        sb.append(selectOption(type,"int"));
        sb.append(selectOption(type,"double"));
        sb.append(selectOption(type,"varchar(40)"));
        sb.append("</select> ");
        
        sb.append("Primary Key: ");
        sb.append("<input ");
        sb.append("type='checkbox' ");
        sb.append("name='pk").append(index).append("' ");
        if(primary){
            sb.append("value='ON' ");
        }
        sb.append("/> ");
        return sb.toString();
    }
    
    private StringBuilder selectOption(String selected, String type){
        StringBuilder sb = new StringBuilder();
        sb.append("<option");
        if(selected.equalsIgnoreCase(type)){
            sb.append(" selected>");
        }else{
            sb.append(">");
        }
        sb.append(type.trim()).append("</option>");
        return sb;
    }
    
    private String getButtonsString(int n){
        StringBuilder sb = new StringBuilder();
        sb.append("<br/>");
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='New Field' ");
        sb.append("/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='Delete Field' ");
        if(n==0){
            sb.append("disabled ");
        }
        sb.append("/>");
        
        sb.append("<br/>");
        
        sb.append("<input ");
        sb.append("type='submit' ");
        sb.append("name='action' ");
        sb.append("value='Create Table' ");
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
