package com.example.baseforall.nyc.data.models


import com.google.gson.annotations.SerializedName

class SchoolListRawResponse : ArrayList<SchoolListRawResponse.SchoolResponseListItem>(){
    data class SchoolResponseListItem(
        @SerializedName("overview_paragraph")
        val overview_paragraph: String?,
        @SerializedName("school_name")
        val school_name: String?,
        @SerializedName("phone_number")
        val phone_number: String?,
        @SerializedName("website")
        val website: String?,
        @SerializedName("latitude")
        val latitude: String?,
        @SerializedName("longitude")
        val longitude: String?,
        @SerializedName("dbn")
        val dbn: String?,
        @SerializedName("location")
        val location: String?
    )
}