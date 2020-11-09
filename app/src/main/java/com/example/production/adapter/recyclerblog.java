package com.example.production.adapter;

public class recyclerblog {

    private String name;
    private String imageId;
    private String blog;
    private String fl;
    private String author;
    private int views;

    public recyclerblog(String name, String blog,String fl,String author,int views) {
        this.name = name;
        this.blog = blog;
        this.fl = fl;
        this.author = author;
        this.views = views;
    }

    public String getName() {
        return name;
    }
    public String getFl(){
        return fl;
    }
    public String getAuthor(){
        return author;
    }
    public  String getBlog(){
        return  blog;
    }
    public int getViews(){
        return  views;
    }
}
