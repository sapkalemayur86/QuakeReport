package com.example.android.quakereport;

public class EarthQuake {

    private  Double mMagnitude;

    private String mLocation;

    private long mDate;

    private String mUrl;



    public EarthQuake(Double magnitude, String location, long date,String url){

        mMagnitude=magnitude;
        mDate=date;
        mLocation=location;
        mUrl=url;
    }

    public Double getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmDate() {
        return mDate;
    }

    public String getmUrl() {
        return mUrl;
    }
}
