package com.example.finintask.utils;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.finintask.R;

import java.io.File;

public class BindingAdapter {
 static int [] color={R.color.a,R.color.b,R.color.c};

    @androidx.databinding.BindingAdapter({"setImageFile"})
    public static void setImageFile(ImageView view, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(view.getContext()).load(url).apply((new RequestOptions()).centerInside()).into(view);
        }

    }
    @androidx.databinding.BindingAdapter("setBackground")
    public static void setbackgroundColor(View view ,String pos) {
      int position=Integer.parseInt(pos);
      view.setBackgroundColor(view.getContext().getResources().getColor(color[position%3]));
    }
    @androidx.databinding.BindingAdapter({"setVisibleInvisible"})
    public static void setVisibleInvisible(View view, boolean visible) {
        if (!visible) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }

    }
}
