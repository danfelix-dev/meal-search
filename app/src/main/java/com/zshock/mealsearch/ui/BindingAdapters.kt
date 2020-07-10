package com.zshock.mealsearch.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.zshock.mealsearch.R

@BindingAdapter("imageUrl")
fun bindImageUrl(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context).load(imageUrl.replace("http://", "https://")).into(imageView);
}

@BindingAdapter(value = ["instructions", "ingredients"])
fun bindInstructionsAndIngredients(
    textView: TextView,
    instructions: String,
    ingredients: List<String>
) {
    val sBuilder = StringBuilder()
    if (ingredients.isNotEmpty()) {
        sBuilder.append(textView.context.getString(R.string.detail_ingredients_header))
        for (ingredient in ingredients) {
            sBuilder.append("\n- $ingredient")
        }
        sBuilder.append("\n\n")
    }

    sBuilder.append(textView.context.getString(R.string.detail_instructions_header))
    sBuilder.append("\n$instructions")

    textView.text = sBuilder.toString()
}