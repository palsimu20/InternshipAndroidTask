package com.example.instershipandroidtask.modelclass;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Data  implements Serializable {
    @SerializedName("id")
    public  String id;
    @SerializedName("name")
    public  String name;
    @SerializedName("email")
    public String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Data(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
