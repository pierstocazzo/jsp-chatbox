/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.util.Date;

/**
 *
 * @author zaniar
 */
public class Message {
    public int senderId;
    public String senderName;
    public int receiverId;
    public String receiverName;
    public Date time;
    public String message;

    public Message(int sid, int rid, String msg){
        this.senderId = sid;
        this.receiverId = rid;
        this.message = msg;
        this.time = new Date();
    }
}
