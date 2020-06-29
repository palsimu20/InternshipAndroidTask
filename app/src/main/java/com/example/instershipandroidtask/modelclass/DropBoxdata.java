package com.example.instershipandroidtask.modelclass;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DropBoxdata {
    @SerializedName("page")
    public String page;
    @SerializedName("data")
    public List<Data>data;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public DropBoxdata(String page, List<Data> data) {
        this.page = page;
        this.data = data;
    }
}
