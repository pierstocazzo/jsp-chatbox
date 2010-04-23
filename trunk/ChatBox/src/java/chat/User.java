/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.sql.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaniar
 */
public class User {
    public int userId;
    public String userName;
    public Date joinTime;

    public User(int uid){
        try {
            this.userId = uid;
            this.joinTime = new Date();
            ResultSet rs;
            Database.getInstance().connect();
            String sql = "SELECT nama FROM user WHERE iduser = '" + uid + "'";
            rs = Database.getInstance().stmt.executeQuery(sql);
            while(rs.next())
                this.userName = rs.getString("nama");
            Database.getInstance().close();
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long joinDuration(){
        return new Date().getTime() - this.joinTime.getTime();
    }
}
