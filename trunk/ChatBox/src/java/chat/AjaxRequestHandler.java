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
import java.util.regex.Pattern;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author zaniar
 */
public class AjaxRequestHandler extends HttpServlet {
    private Connection conn;
    private Rooms rooms;
    private Chats chats;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String tab = request.getParameter("tab");
        String act = request.getParameter("act");
        Integer userId = (Integer) request.getSession().getAttribute("uid");
        Integer role = (Integer) request.getSession().getAttribute("role");
        String roomname = request.getParameter("roomname");
        String friendname = request.getParameter("friendname");
        String kode = request.getParameter("kode");

        this.rooms = (Rooms) getServletContext().getAttribute("Rooms");
        this.chats = (Chats) getServletContext().getAttribute("Chats");
        this.conn = (Connection) getServletContext().getAttribute("Connection");

        PrintWriter out = response.getWriter();

        if(tab != null) {
            if(tab.equals("rooms")){
                this.getRoomList(request,response);
            } else if(tab.equals("friends")){
                this.getFriendList(request,response);
            } else if(tab.equals("room")){
                
            } else if(tab.equals("chat")){
                
            }
        }

        if(act != null) {
            if(act.equals("create")){
                if(role == 1){
                    try {
                        Statement stmt = this.conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT id_prodi FROM user WHERE iduser = " + userId);
                        rs.first();
                        int kodep = rs.getInt("id_prodi");
                        this.rooms.create(roomname, userId, kodep);
                    } catch (SQLException ex) {
                        Logger.getLogger(AjaxRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if(role == 2){
                    this.rooms.create(roomname, userId, Integer.parseInt(kode));
                }
            }
            if(act.equals("join")){
                this.rooms.getRoom(roomname).join(userId);
            }

            if(act.equals("chat")){

            }

            if(act.equals("addfriend")){
                this.makeAFriend(userId,friendname);
            }

            if(act.equals("exit")){

            }

            if(act.equals("info")){

            }

            if(act.equals("kick")){

            }

            if(act.equals("ban")){

            }

            if(act.equals("unban")){

            }

            if(act.equals("persist")){

            }

            if(act.equals("temp")){

            }

            if(act.equals("setowner")){
            
            }
        }

        out.close();
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
        Integer userId = (Integer) request.getSession().getAttribute("uid");
        PrintWriter out = response.getWriter();
  
        try {
            String sql = "SELECT * FROM fakprod WHERE parent = 0";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            out.println("<h2>Room List</h2>");
            out.println("<div><a href=\"#\" onclick=\"refreshRoom()\">refresh</a></div>");
            while(rs.next()){
                int fid = rs.getInt("id");
                String fname = rs.getString("nama");
                out.println("<div class=\"fakultas\">("+fid+") "+fname+"</div>");
                
                Vector<Room> vr = this.rooms.getByKode(rs.getInt("id"));
                Iterator<Room> vri = vr.iterator();
                Room r;
                while(vri.hasNext()){
                    r = vri.next();
                    out.println("<div class=\"fakultas-room\">#"+r.name+"</div>");
                }
                
                sql = "SELECT * FROM fakprod WHERE parent = "+rs.getInt("id");
                Statement stmt2 = this.conn.createStatement();
                ResultSet rs2 = stmt2.executeQuery(sql);
                while(rs2.next()){
                    int pid = rs2.getInt("id");
                    String pname = rs2.getString("nama");
                    out.println("<div class=\"prodi\">("+pid+") "+pname+"</div>");
                    
                    Vector<Room> vr2 = this.rooms.getByKode(rs2.getInt("id"));
                    Iterator<Room> vri2 = vr2.iterator();
                    Room r2;
                    while(vri2.hasNext()){
                        r2 = vri2.next();
                        out.println("<div class=\"prodi-room\">#"+r2.name+"</div>");
                    }
                    
                }
            }
        } catch (Exception ex) {
        } finally {
            out.close();
        }
    }

    private void getFriendList(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Integer userId = (Integer) request.getSession().getAttribute("uid");
        PrintWriter out = response.getWriter();
        String view = request.getParameter("view");

        try {
            String sql;
            if(view.equals("online")){
                sql = "SELECT * FROM user,friend WHERE friend.friend = user.iduser AND friend.id_user = "+userId+" AND user.active = 1";
            } else {
                sql = "SELECT * FROM user,friend WHERE friend.friend = user.iduser AND friend.id_user = "+userId;
            }
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            out.println("<h2>Friend List</h2>");
            out.println("<a href=\"#\" onclick=\"refreshFriend('all')\">all</a>");
            out.println("<a href=\"#\" onclick=\"refreshFriend('online')\">online</a>");
            while(rs.next()){
                String fname = rs.getString("nama");
                out.println("<div>"+fname+"</div>");
            }
        } catch (Exception ex) {
        } finally {
            out.close();
        }
    }

    private void makeAFriend(Integer meId, String he) {
        try {
            Statement stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT iduser FROM user WHERE username = '" + he +"'");
            rs.first();
            int heId = rs.getInt("iduser");

            stmt.executeUpdate("INSERT INTO friend (id_user, friend) VALUES ("+meId+","+heId+")");
        } catch (SQLException ex) {
            Logger.getLogger(AjaxRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
