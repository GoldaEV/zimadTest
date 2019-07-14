package by.golda.zimadtest.model


import com.google.gson.annotations.SerializedName

data class PetModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String
)