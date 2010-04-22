<%-- 
    Document   : admin-login
    Created on : Apr 21, 2010, 1:12:13 PM
    Author     : zaniar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Login</title>
    </head>
    <body>
        <h1>Admin Login</h1>
        <form name="admin-login" action="../AdminLogin" method="POST">
            <input type="text" name="name" value="" /><br/>
            <input type="password" name="password" value="" /><br/>
            <input type="submit" value="Login" name="login" />
        </form>
    </body>
</html>
