package com.example.androiderestaurant.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Price (
    @SerializedName("price") val Price: String,
): Serializable
