package com.example.finintask.view.fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finintask.R;
import com.example.finintask.databinding.FragmentMainBinding;
import com.example.finintask.model.BaseModel;
import com.example.finintask.model.UserModel;
import com.example.finintask.utils.UserListAdapter;
import com.example.finintask.viewModel.MainViewModel;
import com.example.finintask.viewModel.MainViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class MainFragment  extends BaseFragment {
    public static final String TAG=MainFragment.class.getName();
    private FragmentMainBinding binding;
    private MainViewModel viewModel;
    private MainViewModelFactory mainViewModelFactory;
    private LinearLayoutManager linearLayoutManager;
    List<UserModel> userList=new ArrayList<>();
    UserListAdapter listAdapter;
    private int page=1;
    boolean completeApiCycle=true;
    boolean isOnline;


    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int horizontal, int vertical) {
            super.onScrolled(recyclerView, horizontal, vertical);

            if (vertical > 0) {
                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();


                if ((visibleItemCount + pastVisibleItems) >= totalItemCount
                        && page<3
                        && completeApiCycle) {
                    completeApiCycle = false;
                    showFooterLoader(false);
                    viewModel.getListOfUser(page);
                }else {
                    showFooterLoader(true);
                }

            }
        }
    };




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

        }catch (ClassCastException e){

        }


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel=  ViewModelProviders.of(this).get(MainViewModel.class);
        binding.rcvUserList.setLayoutManager(new LinearLayoutManager(getContext()));
        linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.rcvUserList.setLayoutManager(linearLayoutManager);
        binding.rcvUserList.addOnScrollListener(onScrollListener);
        binding.sRefresh.setOnRefreshListener(this::clearDataAndRecall);
        observeUserList();
    }

    private void observeUserList() {
        binding.setIsLoading(true);
        viewModel.getListOfUser(page).observe(this, new Observer<BaseModel<List<UserModel>>>() {
            @Override
            public void onChanged(BaseModel<List<UserModel>> listBaseModel) {
                if (listBaseModel != null) {
                    completeApiCycle=true;
                    page=listBaseModel.getPage()+1;
                      userList.addAll(listBaseModel.getData());
                      setAdapter();
                    viewModel.clearList();
                    binding.setIsLoading(false);
                }


            }
        });

    }

    public void clearDataAndRecall() {
        if (isOnline) {
            viewModel.clearList();
            userList.clear();
            if (listAdapter != null) {
                listAdapter.notifyDataSetChanged();
            }
            binding.sRefresh.setRefreshing(false);

            page = 1;
            binding.setIsLoading(true);
            observeUserList();
        }else{
            binding.sRefresh.setRefreshing(false);
        }
    }

    private void showFooterLoader(boolean b) {
        if(listAdapter!=null){
            listAdapter.showLoader(b);
        }
    }


    private void setAdapter() {
        if(listAdapter==null) {
            listAdapter = new UserListAdapter(userList);
            binding.rcvUserList.setAdapter(listAdapter);
        }else{
            listAdapter.notifyDataSetChanged();

        }

    }

    public void showConnectivityStatus(boolean isConnected) {
        binding.setIsConnected(!isConnected);
         isOnline = isConnected;
        binding.setIsLoading(false);
        if(isConnected){
            clearDataAndRecall();
        }
    }
}