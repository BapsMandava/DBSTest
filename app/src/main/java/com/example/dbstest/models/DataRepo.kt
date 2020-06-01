package com.example.dbstest.models

import com.google.gson.annotations.SerializedName


data class DataRepo(
    @SerializedName("id") var Id: Int,
    @SerializedName("title") var tile: String,
    @SerializedName("last_update") var last_update: Long,
    @SerializedName("short_description") var short_description: String,
    @SerializedName("avatar") var avatar: String
)