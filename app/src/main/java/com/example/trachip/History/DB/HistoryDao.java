package com.example.trachip.History.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trachip.IpResponse;

import java.util.List;

@Dao
public interface HistoryDao {

    @Insert
    void insert(HistoryModel model);

    @Query("SELECT * FROM History ORDER BY timestamp DESC")
    LiveData<List<HistoryModel>> getAll();


    @Query("DELETE FROM History")
    void clearAll();
}
