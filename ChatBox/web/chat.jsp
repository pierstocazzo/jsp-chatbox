<%-- 
    Document   : chat
    Created on : Apr 22, 2010, 10:48:50 AM
    Author     : zaniar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChatBox</title>
        <script type="text/javascript" src="js/lib.js"></script>
        <script type="text/javascript" src="js/chat.js"></script>
        <link media="screen" rel="stylesheet" href="css/chat.css" type="text/css"/>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="title">ChatBox</div>
                <div id="logout"><a href="Logout">LogOut</a></div>
            </div>
            <div id="tabs">
                <ul><li id="rooms" class="tab">Rooms</li><li id="friends" class="tab">Friends</li></ul>
            </div>
            <div id="main">
            </div>
            <div id="input">
                <input id="inputline" type="text" name="inputline" value="" />
            </div>
        </div>
    </body>
</html>
