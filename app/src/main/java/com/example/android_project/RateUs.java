package com.example.android_project;

public class RateUs {

    private int id;
    private String rateUs;

    public RateUs(String rateUs) {
        this.rateUs = rateUs;
    }
    public RateUs(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRateUs() {
        return rateUs;
    }

    public void setRateUs(String rateUs) {
        this.rateUs = rateUs;
    }
}
