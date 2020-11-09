package com.example.production.uitl;

import com.example.production.adapter.recyclerblog;

public class PublicData {
    public static recyclerblog r;
    public static void setRecyclerblog(recyclerblog rec){
        r = rec;
    }
    public static recyclerblog getRecyclerblog(){
        return r;
    }
}
