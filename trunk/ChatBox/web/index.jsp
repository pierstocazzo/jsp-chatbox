<%--
    Document   : index
    Created on : Apr 20, 2010, 11:27:49 AM
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
        <title>ChatBox</title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/index.css" title="default"/>
        <script type="text/javascript" src="js/fakultas.js"></script>
    </head>
    <body>
        <div class="login">
            <div id="title">ChatBox</div>
            <form method="post" action="Login">
                <label for="usernameL">Username</label>
                <input type="text" id="usernameL" name="usernameL" value=""/>
                <label for="passwordL">Password</label>
                <input type="password" id="passwordL" name="passwordL" value=""/>
                <input class="button" type="submit" value="Login" />
            </form>
        </div>
        <div class="register">
            <h1>Registrasi</h1>
            <form method="post" action="Register">
                <label for="username">Username </label>
                <input type="text" id="username" name="username" /><br />
                <label for="password">Password </label>
                <input type="password" id="password" name="password" /><br />
                <label for="konfirmasi">Konfirmasi </label>
                <input type="password" id="konfirmasi" name="konfirmasi" /><br />
                <label for="nama">Nama </label>
                <input type="text" id="nama" name="nama" /><br />
                <label for="email">Email </label>
                <input type="text" id="email" name="email" /><br />
                <%
                        try{
                        out.println("<label for='fakultas'>Fakultas </label>");
                        out.println("<select id='fakultas' name='fakultas' onchange='javascript:loadProdi(this.value)'>");

                        Statement stmt, stmt2;
                        ResultSet rs1,rs2;
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/chatbox";
                        Connection con = DriverManager.getConnection(url,"zaniar","zaniar");
                        stmt = con.createStatement();
                        rs1 = stmt.executeQuery("SELECT * FROM fakprod WHERE tipe = 0");

                        int i = 0;
                        int selected = 0;
                        while (rs1.next()) {
                            int id = rs1.getInt("id");
                            if (i == 0)
                                selected = id;
                            out.println("<option value="+id+">");
                            out.println(rs1.getString("nama"));
                            out.println("</option>");
                            i++;
                        }
                        out.println("</select><br />");
                        rs2 = stmt.executeQuery("SELECT * FROM fakprod WHERE tipe=1 AND parent="+selected);
                        out.println("<div id='optProdi'>");
                        out.println("<label for='prodi'>Prodi </label>");
                            out.println("<select>");
                            while (rs2.next()) {
                                out.println("<option value="+rs2.getInt("id")+">");
                                out.println(rs2.getString("nama"));
                                out.println("</option>");
                                i++;
                            }
                        out.println("</select>");
                        out.println("</div> ");
                        con.close();
                        }
                        catch (Exception e){
                        }
                    %>
                <input class="button" type="submit" value="Register" />
            </form>
        </div>
    </body>
</html>
