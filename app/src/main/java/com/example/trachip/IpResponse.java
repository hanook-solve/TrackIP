package com.example.trachip;

import com.google.gson.annotations.SerializedName;

public class IpResponse {
    @SerializedName("query")
    private String ip;

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("lat")
    private double lat;

    @SerializedName("lon")
    private double lon;

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
