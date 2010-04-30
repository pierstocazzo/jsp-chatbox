<%-- 
    Document   : showFakProdi
    Created on : 22 Apr 10, 18:02:44
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="com.mysql.jdbc.Connection" %>
<%@ page import="com.mysql.jdbc.Statement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.ResultSet" %>

<%
    String Fak = request.getParameter("fak");
    
    try {
        Connection con;
        Statement stmt;

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/chatbox";
        con = (Connection) DriverManager.getConnection(url,"root","root");
        stmt = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM FAKPROD WHERE tipe = 1 AND parent="+Fak);

        out.println("<label for='prodi'>Prodi </label>");
        out.println("<select id='prodi' name='prodi'>");
        while (rs.next()) {
            out.println("<option value="+rs.getInt("id")+">");
            out.println(rs.getString("nama"));
            out.println("</option>");
        }
        out.println("</select>");
        
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
