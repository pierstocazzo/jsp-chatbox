<%-- 
    Document   : admin
    Created on : Apr 21, 2010, 9:06:16 PM
    Author     : Neo Enriko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/admin.css" title="default"/>
        <script src="js/admin.js" type="text/javascript"></script>
    </head>
    <body>
        <div id="top">
            <div class="topblock">
                <div class="menu">Admin Page</div>
                <span><form method="post" action="Logout"><button type="submit">logout</button></form></span>
            </div>
        </div>
        <div class="right-content">
            <div class="right-block">
            <b><p>Tambah Fakultas:</p></b>
            <form method="post" action="admin.jsp">
                <input name="newfak" value=""/>
                <span class="klik"><button type="submit">OK</button></span>
            </form>

            <b><p>Fakultas:</p></b>
            <%
            try {
            Statement fak, prod, newfak, newprod, del_prod, del_fak, del_fakprod;
            ResultSet rsfak, rsprod;
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/chatbox";
            Connection con = DriverManager.getConnection(url, "root", "");
            if(!con.isClosed())
            {
                if (request.getParameter("newfak")!=null)
                {
                String fak_baru = request.getParameter("newfak");
                newfak = con.createStatement();
                newfak.executeUpdate("INSERT INTO fakprod (tipe,nama,parent) VALUES ('0', '"+fak_baru+"', '0')");
                }
                if (request.getParameter("newprod")!=null){
                    String prodi_baru = request.getParameter("newprod");
                    String parent = request.getParameter("idprod");
                    newprod = con.createStatement();
                    newprod.executeUpdate("INSERT INTO fakprod (tipe,nama,parent) VALUES ('1', '"+prodi_baru+"', '"+parent+"')");
                }
                if (request.getParameter("del")!=null){
                    String id_del = request.getParameter("del");
                    del_prod = con.createStatement();
                    del_prod.executeUpdate("DELETE FROM fakprod WHERE id="+id_del+"");
                }
                if (request.getParameter("delfak")!=null){
                    String id_delfak = request.getParameter("delfak");
                    del_fak = con.createStatement();
                    del_fak.executeUpdate("DELETE FROM fakprod WHERE id="+id_delfak+"");
                    del_fakprod = con.createStatement();
                    del_fakprod.executeUpdate("DELETE FROM fakprod WHERE parent="+id_delfak+"");
                }
            fak = con.createStatement();
            rsfak = fak.executeQuery("SELECT * FROM fakprod WHERE tipe='0' ORDER BY id ASC");
            while(rsfak.next()){
                out.println("<div class=\"fakultas\">");
                int id = rsfak.getInt("id");
                String name = rsfak.getString("nama");
                out.println("<div class=\"nama-fakultas\"><p class=\"fak\">"+name+"</p>");
                out.println("<span class=\"hapus-fak\"><a href=\"admin.jsp?delfak="+id+"\">hapus</a></span></div>");
                prod = con.createStatement();
                rsprod = prod.executeQuery("SELECT * FROM fakprod WHERE tipe='1' AND parent='"+id+"'");
                out.println("<div class=\"prodi-list\">");
                while (rsprod.next()){
                    String prodname = rsprod.getString("nama");
                    int idfak = rsprod.getInt("id");
                    out.println("<div class=\"prodi\">");
                    out.println(prodname);
                    out.println("<span class=\"hapus-prodi\"><a href=\"admin.jsp?del="+idfak+"\">hapus</a></span>");
                    out.println("</div>");
                }
                out.println("</div>");
                    out.println("<form class=\"newprod\" method=\"post\" action=\"admin.jsp\">Prodi Baru:");
                    out.println("<input type=\"text\" name=\"newprod\" id=\"id\" value=\"\"/>");
                    out.println("<input type=\"hidden\" name=\"idprod\" value=\""+id+"\"/>");
                    out.println("<button class=\"klik\">OK</button>");
                    out.println("</form>");
                out.println("</div>");
            }
            out.println("");
            con.close();
            }
            }
            catch (Exception e) {
                
            }
            %>
            </div>
        </div>
        <div class="left-content">
            <div class="left-block">
            <b><p>Pengaturan User:</p></b>
            <table width="580px" cellspacing="0">
                <thead><tr >
                      <td width="5%" style="text-align:center" >No.</td>
                      <td width="20%">Username</td>
                      <td width="30%">Nama lengkap</td>
                      <td width="30%">Email</td>
                      <td width="30%">Ubah status</td>
                </tr></thead>
                <%
                    int i=1;
                    Statement user, ubah;
                    ResultSet data;
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/chatbox";
                    Connection con = DriverManager.getConnection(url, "root", "");
                    if(!con.isClosed())
                    {
                        if (request.getParameter("user")!=null){
                            String newrole =  request.getParameter("role");
                            String newuser = request.getParameter("user");
                            ubah = con.createStatement();
                            ubah.executeUpdate("UPDATE user SET role="+newrole+" WHERE iduser="+newuser+"");
                        }
                        user = con.createStatement();
                        data = user.executeQuery("SELECT * FROM user");
                        out.println("<tbody>");
                        int count=1;
                        while (data.next()){
                            out.println("<tr><td style=\"text-align:center\">"+count+"</td>");
                            String username = data.getString("username");
                            out.println("<td>"+username+"</td>");
                            String nama = data.getString("nama");
                            out.println("<td>"+nama+"</td>");
                            String email = data.getString("email");
                            out.println("<td>"+email+"</td>");
                            int status = data.getInt("role");
                            int userid = data.getInt("iduser");
                            if (status==1){
                            out.println("<td><a href=\"admin.jsp?role=2&user="+userid+"\">std_user</a></td></tr>");}
                            else if (status==2){out.println("<td><a href=\"admin.jsp?role=1&user="+userid+"\">dosen</a></td></tr>");}
                            count++;
                        }
                        out.println("</tbody>");
                    }

                %>
            </table>
            </div>
        </div>
    </body>
</html>
