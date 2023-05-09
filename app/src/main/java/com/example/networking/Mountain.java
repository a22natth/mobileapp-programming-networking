package com.example.networking;

import com.google.gson.annotations.SerializedName;

public class Mountain {

    @SerializedName("ID")
    private String id;
    private String name;
    private String type;
    private String company;
    private String location;
    private String category;
    @SerializedName("size")
    private int meters;
    @SerializedName("cost")
    private Auxdata auxdata;


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

    @Override
    public String toString() {
        return name;
    }
}

