package com.example.trachip.History.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.trachip.IpResponse;


@Entity(tableName = "History")
public class HistoryModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long timestamp;
    private String ip;
    private String country;
    private String city;
    private double lat;
    private double lon;

//    public HistoryModel(int id, long timestamp, String ip, String country, String city, double lat, double lon) {
//        this.id = id;
//        this.timestamp = timestamp;
//        this.ip = ip;
//        this.country = country;
//        this.city = city;
//        this.lat = lat;
//        this.lon = lon;
//    }

    // ✅ Correct — Room handles id automatically
    public HistoryModel(String ip, String country, String city, double lat, double lon, long timestamp) {
        this.ip = ip;
        this.country = country;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
        this.timestamp = timestamp;
    }


    //getters
    public int getId(){return id;}
    public long getTimestamp() {return timestamp;}
    public String getIp() {return ip;}
    public String getCountry(){return country;}
    public String getCity() {return city;}
    public double getLat(){return lat;}
    public double getLon(){return lon;}

    // Add this setter — Room needs it to set the auto-generated id
    public void setId(int id) {
        this.id = id;
    }


}


