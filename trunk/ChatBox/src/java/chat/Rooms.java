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
public class Rooms {
    public Vector<Room> rooms;

    public Rooms(){
        this.rooms = new Vector<Room>();
    }

    public boolean isExist(String name){
        boolean found = false;
        Room e;
        Iterator<Room> i = this.rooms.iterator();
        while(i.hasNext()){
            e = i.next();
            if(e.name.equals(name)){
                found = true;
                break;
            }
        }
        return found;
    }

    public int roomIndex(String name){
        int idx = 0;
        Room e;
        Iterator<Room> i = this.rooms.iterator();
        while(i.hasNext()){
            e = i.next();
            if(e.name.equals(name)){
                break;
            }
            idx++;
        }
        return idx;
    }

    public void create(String name, int oid, int kode){
        if(!this.isExist(name)){
            this.rooms.add(new Room(name,oid,kode));
        }
    }

    public void remove(String name){
        this.rooms.removeElementAt(this.roomIndex(name));
    }

    public void exit(String name){
        Room r = this.rooms.get(this.roomIndex(name));
        if(r.users.size() == 1){
            if(!r.persistence){
                this.remove(name);
            }
        }
    }

    public Vector<Room> getByKode(int kd){
        Vector<Room> ret = new Vector<Room>();
        Iterator<Room> i = this.rooms.iterator();
        Room r;

        while(i.hasNext()){
            r = i.next();
            if(r.kode == kd){
                ret.add(r);
            }
        }

        return ret;
    }

    public Room getRoom(String name){
        Iterator<Room> i = this.rooms.iterator();
        Room r;

        while(i.hasNext()){
            r = i.next();
            if(r.name.equals(name)){
                return r;
            }
        }

        return null;
    }
}
