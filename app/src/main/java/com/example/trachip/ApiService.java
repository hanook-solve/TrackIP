package com.example.trachip;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("json/{ip}")
    Call<IpResponse> getIpInfo (@Path("ip") String ip);
}
