package com.example.laptrinhdidong_lab8_12_4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Faces.class}, version = 1)
public abstract class DatabaseFaces extends RoomDatabase {

    public abstract FacesDAO facesDAO();

}
