package com.example.production.adapter;

public class recyclerchat {

    public static final int TYPE_RECEIVED = 0; // left
    public static final int TYPE_SENT = 1; // right
    private int id;
    private String chat;
    private int type;

    public recyclerchat(int id,String chat, int type) {
        this.id = id;
        this.chat = chat;
        this.type = type;
    }
    public int getId(){return  id;}
    public String getChat() {
        return chat;
    }
    public int getType(){
        return type;
    }
}
