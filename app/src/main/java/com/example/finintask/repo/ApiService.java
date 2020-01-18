package com.example.finintask.repo;

import com.example.finintask.model.BaseModel;
import com.example.finintask.model.UserModel;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ApiService {
    String data = "users";
  @GET(data)
    Call <BaseModel<List<UserModel >>> getUserList(@Query("page") int page, @Query("delay") int delay);

}
