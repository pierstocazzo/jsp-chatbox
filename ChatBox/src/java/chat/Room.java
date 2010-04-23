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
public class Room {
    public String name;
    public int ownerId;
    public String ownerName;
    public int fakultasId;
    public int prodiId;
    public boolean persistence;
    public Vector<User> users;
    public Vector<Integer> bannedUsers;
    public Vector<Message> message;

    public Room(String n, int oid, int fid, int pid){
        this.name = n;
        this.ownerId = oid;
        this.fakultasId = fid;
        this.prodiId = pid;
    }
    
    public void join(int uid){
        this.users.add(new User(uid));
    }

    public void kick(int uid){
        int idx = this.userIndex(uid);
        this.users.removeElementAt(idx);
    }

    public int userIndex(int uid){
        Iterator<User> i = this.users.iterator();
        User e;
        int idx = 0;
        while(i.hasNext()){
            e = i.next();
            if(e.userId == uid){
                break;
            }
            idx++;
        }

        return idx;
    }

    public void ban(int uid){
        this.bannedUsers.add(uid);
        this.kick(uid);
    }
}
