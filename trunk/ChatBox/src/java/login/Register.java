/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package login;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Neo Enriko
 */
public class Register extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String Username;
        String Password;
        String Konfirmasi;
        String Nama;
        String Email;
        String Fak;
        String Prodi;

        Username =  request.getParameter("username");
        Password =  request.getParameter("password");
        Konfirmasi = request.getParameter("konfirmasi");
        Nama = request.getParameter("nama");
        Email = request.getParameter("email");
        Fak = request.getParameter("fakultas");
        Prodi = request.getParameter("prodi");

        if (Password.equalsIgnoreCase(Konfirmasi)) {
            try {
                Connection con;
                Statement stmt,stmt4;
                ResultSet prod;
                int iduser;

                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/chatbox";
                con = (Connection) DriverManager.getConnection(url,"root","");

                if (Prodi==null){
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    stmt4 = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    prod = stmt4.executeQuery("SELECT * FROM fakprod WHERE tipe=1 AND parent="+Fak);
                    prod.next();
                    Prodi = prod.getString("id");
                }
                stmt = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.executeUpdate("INSERT USER (username,password,nama,email,id_fak,id_prodi) VALUES ('"+Username+"','"+Password+"','"+Nama+"','"+Email+"',"+Fak+","+Prodi+")");

                Statement stmt3 = (Statement) con.createStatement();
                ResultSet rs2 = stmt3.executeQuery("SELECT * FROM USER WHERE username = '"+Username +"' AND password = '"+Password+"'");
                rs2.next();
                iduser=rs2.getInt("iduser");


                Statement stmt2 = (Statement) con.createStatement();
                stmt2.executeUpdate("UPDATE USER SET active=1 WHERE username = '"+Username +"' AND password = '"+Password+"'");

                HttpSession session =  request.getSession(true);
                session.setAttribute("uid", iduser);
                session.setAttribute("username", Username);
                session.setAttribute("role", 1);

                response.sendRedirect("chat.jsp");
                con.close();
            } catch (Exception e) {
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println(e);
                e.printStackTrace();
            }
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

}
