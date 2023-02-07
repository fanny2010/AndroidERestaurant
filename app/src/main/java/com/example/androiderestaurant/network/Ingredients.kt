package com.example.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Ingredients (
        @SerializedName("ingredients") val ingredients: String,
): Serializable