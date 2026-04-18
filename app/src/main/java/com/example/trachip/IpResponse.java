package com.example.trachip;

import com.google.gson.annotations.SerializedName;

public class IpResponse {
    @SerializedName("query")
    public String ip;

    @SerializedName("country")
    public String country;

    @SerializedName("city")
    public String city;

    @SerializedName("lat")
    public double lat;

    @SerializedName("lon")
    public double lon;

    @SerializedName("status")
    private String status;

    //Getter
    public String getIp(){return ip; }
    public String getCountry() {return country;}
    public String getCity() {return city;}
    public double getLat() {return lat;}
    public double getLon() {return lon;}
    public String getStatus() {return status;}
}
