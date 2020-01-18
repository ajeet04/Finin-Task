package com.example.finintask.repo;

import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.finintask.model.BaseModel;
import com.example.finintask.model.UserModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserListRepo {
    Retrofit retrofit;
    public  static UserListRepo ourInstance=new UserListRepo();
    private MutableLiveData<BaseModel<List<UserModel>>> data=new MutableLiveData<>();

    public static UserListRepo getInstance() {
        return ourInstance;
    }

    public MutableLiveData<BaseModel<List<UserModel>>>  getUserList(int pageNo) {



        retrofitClient().getUserList(pageNo,3).enqueue(new Callback<BaseModel<List<UserModel>>>() {
            @Override
            public void onResponse(Call<BaseModel<List<UserModel>>> call, Response<BaseModel<List<UserModel>>> response) {
                data.setValue(response.body());
                Log.d("API DATA",response.body().toString());
            }

            @Override
            public void onFailure(Call<BaseModel<List<UserModel>>> call, Throwable t) {

            }

        });

        return data;
    }

    public void clearList() {
        data.setValue(null);
    }

    public ApiService retrofitClient(){
        if (retrofit == null) {
            retrofit = (new Retrofit.Builder()).baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build();
        }

        return (ApiService)retrofit.create(ApiService.class);
    }

}
