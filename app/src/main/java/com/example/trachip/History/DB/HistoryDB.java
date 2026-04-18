package com.example.trachip.History.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {HistoryModel.class}, version = 1)
public abstract class HistoryDB extends RoomDatabase {
    public static HistoryDB instance;
    public abstract HistoryDao historyDao();

    public static HistoryDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                   context,
                   HistoryDB.class,
                   "history_db"
            ).fallbackToDestructiveMigration() .build();
        }
        return instance;
    }

}
