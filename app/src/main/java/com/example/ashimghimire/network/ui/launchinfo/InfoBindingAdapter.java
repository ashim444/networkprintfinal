package com.example.ashimghimire.network.ui.launchinfo;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ashimghimire.network.R;

import androidx.databinding.BindingAdapter;

public class InfoBindingAdapter {

    @BindingAdapter("imageurl")
    public static void setImageResource(ImageView view, String imageUrl) {
        Context context = view.getContext();
        Glide.with(context).load(imageUrl).placeholder(R.drawable.ic_launcher_background).into(view);
    }
}
