package by.golda.zimadtest.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)