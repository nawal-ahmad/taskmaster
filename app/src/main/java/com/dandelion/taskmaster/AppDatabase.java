package com.dandelion.taskmaster;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
