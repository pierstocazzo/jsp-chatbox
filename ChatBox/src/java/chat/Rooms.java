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
    private static Rooms instance = null;
    public Vector<Room> rooms;

    private Rooms(){
        this.rooms = new Vector<Room>();
    }

    public static Rooms getInstance(){
        if(instance == null) {
            instance = new Rooms();
        }

        return instance;
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

    public void create(String name, int oid, int fid, int pid){
        if(!this.isExist(name)){
            this.rooms.add(new Room(name,oid,fid,pid));
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

    public Vector<Room> getByFakultasId(int fid){
        Vector<Room> ret = new Vector<Room>();
        Iterator<Room> i = this.rooms.iterator();
        Room r;

        while(i.hasNext()){
            r = i.next();
            if(r.fakultasId == fid){
                ret.add(r);
            }
        }

        return ret;
    }

    public Vector<Room> getByProdiId(int pid){
        Vector<Room> ret = new Vector<Room>();
        Iterator<Room> i = this.rooms.iterator();
        Room r;

        while(i.hasNext()){
            r = i.next();
            if(r.prodiId == pid){
                ret.add(r);
            }
        }

        return ret;
    }
}
