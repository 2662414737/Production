package com.example.production.adapter;

public class recyclerhy {
    private String name; // 用户名

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username; // 用户账号

    @Override
    public String toString() {
        return "recyclerhy{" +
                "name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;

    }

    public recyclerhy(String name,String username){
        this.name = name;
        this.username = username;
    }
    public String getName(){
         return name;
    }

}
