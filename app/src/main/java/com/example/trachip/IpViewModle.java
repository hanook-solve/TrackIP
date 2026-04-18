package com.example.trachip;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.trachip.History.DB.HistoryDB;
import com.example.trachip.History.DB.HistoryDao;
import com.example.trachip.History.DB.HistoryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IpViewModle extends AndroidViewModel {
    public HistoryDB db;
    private final MutableLiveData<IpResponse> ipResult = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    // This creates an INSTANCE of ApiService
    private final ApiService apiService = ApiClient.getService();

    public IpViewModle(@NonNull Application application) {
        super(application);
        db = HistoryDB.getInstance(application);

    }

    //getter <LiveData>

    public LiveData<IpResponse> getIpResult() {
        return ipResult;
    }
    public LiveData<String> getErrorMessage(){
        return errorMessage;
    }
    public LiveData<Boolean> getIsLoading(){
        return isLoading;
    }



    public void lookupIp(String ip) {
        isLoading.setValue(true);



        apiService.getIpInfo(ip).enqueue(new Callback<IpResponse>() {

            @Override
            public void onResponse(Call<IpResponse> call, Response<IpResponse> response) {
                isLoading.setValue(false);

                if (response.isSuccessful() && response.body() != null) {
                    IpResponse data = response.body();

                    if ("success".equals(data.getStatus())) {
                        ipResult.setValue(data);
                        new Thread(() -> {
                            HistoryModel entity = toEntity(data);
                            db.historyDao().insert(entity);
                        }).start();

                    } else {
                        errorMessage.setValue("Invalid IP address. Please try again.");
                    }
                } else {
                    errorMessage.setValue("Unexpected error. Try again.");
                }
            }

            @Override
            public void onFailure(Call<IpResponse> call, Throwable t) {
                isLoading.setValue(false);
                Log.e("IPFinder", "Error: " + t.getMessage());

                if (t instanceof java.net.UnknownHostException) {
                    errorMessage.setValue("No internet connection.");
                } else {
                    errorMessage.setValue("Something went wrong. Try again.");
                }
            }

        });

    }

    private HistoryModel toEntity(IpResponse data) {
        return new HistoryModel(
                data.getIp(),
                data.getCountry(),
                data.getCity(),
                data.getLat(),
                data.getLon(),
                System.currentTimeMillis() // timestamp
        );
    }


}
