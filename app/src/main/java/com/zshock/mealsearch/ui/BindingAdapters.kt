package com.zshock.mealsearch.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun bindImageUrl(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context).load(imageUrl.replace("http://", "https://")).into(imageView);
}