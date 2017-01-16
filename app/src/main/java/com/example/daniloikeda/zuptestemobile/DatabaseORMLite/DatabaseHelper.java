package com.example.daniloikeda.zuptestemobile.DatabaseORMLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by DaniloIkeda on 13/01/2017.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<Movies, Integer> movieDao = null;
    private RuntimeExceptionDao<Movies, Integer> movieRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Movies.class);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            TableUtils.dropTable(connectionSource,Movies.class, true);
            onCreate(database, connectionSource);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<Movies, Integer>getMovieDao() throws SQLException{
        if(movieDao == null){
            movieDao = getDao(Movies.class);
        }
        return movieDao;
    }

    public RuntimeExceptionDao<Movies, Integer> getMovieRuntimeExceptionDao(){
        if(movieRuntimeDao == null){
            movieRuntimeDao = getRuntimeExceptionDao(Movies.class);
        }
        return movieRuntimeDao;
    }
}
