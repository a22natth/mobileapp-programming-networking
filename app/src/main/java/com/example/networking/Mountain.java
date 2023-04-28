package com.example.networking;

public class Mountain {

    private String name;
    private String type;

    Mountain(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }
}
