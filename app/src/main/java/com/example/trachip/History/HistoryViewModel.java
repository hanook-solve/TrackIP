package com.example.trachip.History;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.trachip.History.DB.HistoryDB;
import com.example.trachip.History.DB.HistoryModel;
import com.example.trachip.IpResponse;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {
    private HistoryDB db;

    public HistoryViewModel(Application application) {
        super(application);
        db = HistoryDB.getInstance(application);
    }

    public void insert(HistoryModel model) {
        new Thread(() -> {
            db.historyDao().insert(model);
        }).start();
    }
    public LiveData<List<HistoryModel>> getAll() {
        return db.historyDao().getAll();
    }

    public void clearAll() {
        new Thread(() -> {
            db.historyDao().clearAll();
        }).start();
    }


}
