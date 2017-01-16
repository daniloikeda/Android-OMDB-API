package com.example.daniloikeda.zuptestemobile.Retrofit.api;

import com.example.daniloikeda.zuptestemobile.Retrofit.model.gitmodel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by DaniloIkeda on 13/01/2017.
 */
public interface gitapi {
    @GET("/")      //here is the other url part.best way is to start using /
    
    public void getFeed(@Query("t") String user, Callback<gitmodel> response);
    //string user is for passing values from edittext for eg: user=basil2style,google
    //response is the response from the server which is now in the POJO
}