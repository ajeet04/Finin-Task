package com.example.finintask.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finintask.R;
import com.example.finintask.databinding.FooterItemBinding;
import com.example.finintask.databinding.ListItemBinding;
import com.example.finintask.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public  class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UserModel> data=new ArrayList<>();
    final static int FOOTER_TYPE=1;
    final static int ITEM_TYPE=0;
    private boolean showLoader;


    public UserListAdapter(List<UserModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == FOOTER_TYPE) {
            FooterItemBinding binding=DataBindingUtil.inflate(inflater,R.layout.footer_item,parent,false);
            return new FooterHolder(binding);
        } else {
            ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, parent, false);
            return new MyHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder)
            ((MyHolder) holder).bindData();
        if(holder instanceof FooterHolder)
            ((FooterHolder)holder).setLoaderAnim();
    }

    @Override
    public int getItemViewType(int position) {
        return position==data.size()?FOOTER_TYPE:ITEM_TYPE;
    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }
    public void showLoader(boolean isShow){
        showLoader=isShow;
    }


    private class MyHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;
        public MyHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bindData() {
            binding.setUserModel(data.get(getAdapterPosition()));
            binding.setPosition(getAdapterPosition()+1+"");

        }
    }
    private class FooterHolder extends RecyclerView.ViewHolder {
        FooterItemBinding binding;
        public FooterHolder(FooterItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
        public void setLoaderAnim() {

            binding.setIsLoad(showLoader);
;
        }

}

}
