/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaniar
 */
public class Database {
    private static Database instance = null;
    public Connection con;
    private String url;

    protected Database(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.url = "jdbc:mysql://localhost:3306/chatbox";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Database getInstance(){
        if(instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void connect(){
        try {
            this.con = DriverManager.getConnection(this.url, "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
