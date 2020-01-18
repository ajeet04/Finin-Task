package com.example.finintask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.finintask.utils.ApiConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseModel<T> implements Parcelable {


    @SerializedName(ApiConstants.PAGE_KEY)
    @Expose(serialize = false, deserialize = true)
    private int page;
    @SerializedName(ApiConstants.DATA_KEY)
    @Expose
    private T data;
    @SerializedName(ApiConstants.TOTAL_PAGE_KEY)
    @Expose
    private int totalPagesKey;
    @SerializedName(ApiConstants.TOTAL_KEY)
    @Expose(serialize = false, deserialize = true)
    private int total;
    @SerializedName(ApiConstants.PER_PAGES_KEY)
    @Expose(serialize = false, deserialize = true)
    private int perPages;

    protected BaseModel(Parcel in) {
    }

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel in) {
            return new BaseModel(in);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getTotalPagesKey() {
        return totalPagesKey;
    }

    public void setTotalPagesKey(int totalPagesKey) {
        this.totalPagesKey = totalPagesKey;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPerPages() {
        return perPages;
    }

    public void setPerPages(int perPages) {
        this.perPages = perPages;
    }
}
