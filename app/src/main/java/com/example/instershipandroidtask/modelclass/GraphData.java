package com.example.instershipandroidtask.modelclass;

import com.google.gson.annotations.SerializedName;

public class GraphData {
    @SerializedName("date_created")
    public String date_created;
    @SerializedName("amount")
    public int amount;

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public GraphData(String date_created, int amount) {
        this.date_created = date_created;
        this.amount = amount;
    }
}
