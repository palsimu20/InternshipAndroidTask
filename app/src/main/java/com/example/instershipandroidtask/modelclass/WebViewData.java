package com.example.instershipandroidtask.modelclass;

import com.google.gson.annotations.SerializedName;

public class WebViewData {


    @SerializedName("title")
    public String title;
    @SerializedName("content")
    public String content;

    public WebViewData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
