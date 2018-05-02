package com.example.ahmad_pc.myapplication;

public class Msg {

    String name, msg;
    Long time;

    public Msg(String name, String msg, Long time){
        this.name = name;
        this.msg = msg;
        this.time = time;
    }

    public String getText(){
        return msg;
    }

    public String getName(){
        return name;
    }

    public Long getTime(){
        return time;
    }
}
