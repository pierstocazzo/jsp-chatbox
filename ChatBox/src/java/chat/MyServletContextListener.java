/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

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
        String whatType = sc.getInitParameter("typeSelected");
        Rooms r = new Rooms();
        Chats c = new Chats();
        sc.setAttribute("Rooms", r);
        sc.setAttribute("Chats", c);
    }
    
    public void contextDestroyed(ServletContextEvent event)
    {

    }
}
