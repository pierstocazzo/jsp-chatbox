/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zaniar
 */
public class AjaxRequestHandler extends HttpServlet {
    private Statement stmt;
    private ResultSet rs;
    private Connection conn;
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String mode = request.getParameter("mode");

        PrintWriter out = response.getWriter();
        try{
            if(mode.equals("rooms")){
                Rooms.getInstance().create("Room1", 1, 1, 2);
                this.getRoomList(request,response);
            }
            if(mode.equals("friends")){
                out.println("friends list");
            }
            if(mode.equals("cmd")){
            }
        } finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void getRoomList(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Database.getInstance().connect();
            String sql = "SELECT * FROM fakprod WHERE parent = 0";
            Statement stmt = Database.getInstance().con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            out.println("<h2>Room List</h2>");
            while(rs.next()){
                String fname = rs.getString("nama");
                out.println("<div class=\"fakultas\">"+fname+"</div>");
                sql = "SELECT * FROM fakprod WHERE parent = "+rs.getInt("id");
                Statement stmt2 = Database.getInstance().con.createStatement();
                ResultSet rs2 = stmt2.executeQuery(sql);
                while(rs2.next()){
                    String pname = rs2.getString("nama");
                    out.println("<div class=\"prodi\">"+pname+"</div>");
                    Vector<Room> vr = Rooms.getInstance().getByProdiId(rs2.getInt("id"));
                    Iterator<Room> vri = vr.iterator();
                    Room r;
                    while(vri.hasNext()){
                        r = vri.next();
                        out.println("<div class=\"prodi-room\">"+r.name+"</div>");
                    }
                }
            }
        } catch (Exception ex) {
        } finally {
            Database.getInstance().close();
            out.close();
        }
    }

}
