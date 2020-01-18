package com.example.finintask.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.finintask.utils.ApiConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel implements Parcelable {
    @SerializedName(ApiConstants.ID_KEY)
    @Expose(serialize = false, deserialize = true)
    private int id;

    @SerializedName(ApiConstants.FIRST_NAME_KEY)
    @Expose
    private String firstName;
    @SerializedName(ApiConstants.LAST_NAME_KEY)
    @Expose(serialize = false, deserialize = true)
    private String lastname;
    @SerializedName(ApiConstants.AVATAR_KEY)
    @Expose(serialize = false, deserialize = true)
    private String avatar;
    @SerializedName(ApiConstants.EMAIL_KEY)
    @Expose(serialize = false, deserialize = true)
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    protected UserModel(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastname = in.readString();
        avatar = in.readString();
        email = in.readString();
    }
    public String getFullName(){
        return firstName+" "+lastname;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastname);
        parcel.writeString(avatar);
        parcel.writeString(email);
    }


}
