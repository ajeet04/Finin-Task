package com.example.finintask.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.finintask.model.BaseModel;
import com.example.finintask.model.UserModel;
import com.example.finintask.repo.UserListRepo;

import java.util.List;

public class MainViewModel  extends ViewModel {
    private UserListRepo userListRepo;
    public MainViewModel(){
        userListRepo=UserListRepo.getInstance();
    }
    public MutableLiveData<BaseModel<List<UserModel>>> getListOfUser(int page){
        return userListRepo.getUserList(page);
    }

    public void clearList() {
        userListRepo.clearList();
    }
}
