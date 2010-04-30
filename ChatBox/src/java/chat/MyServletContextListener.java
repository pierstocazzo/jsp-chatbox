/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author zaniar
 */
public class MyServletContextListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent event)
    {
        ServletContext sc = event.getServletContext();
        Rooms r = new Rooms();
        Chats c = new Chats();

        sc.setAttribute("Rooms", r);
        sc.setAttribute("Chats", c);

        Database.getInstance().connect();
        sc.setAttribute("Connection", Database.getInstance().con);
    }
    
    public void contextDestroyed(ServletContextEvent event)
    {

    }
}
