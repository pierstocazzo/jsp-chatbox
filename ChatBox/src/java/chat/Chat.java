/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package chat;

import java.util.*;

/**
 *
 * @author zaniar
 */
public class Chat {
    public int firstPersonId;
    public int secondPersonId;
    Vector<Message> message;

    public Chat(int fid, int sid){
        this.firstPersonId = fid;
        this.secondPersonId = sid;
    }
}
