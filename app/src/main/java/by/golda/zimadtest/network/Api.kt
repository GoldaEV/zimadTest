package by.golda.zimadtest.network

import by.golda.zimadtest.model.PetModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/xim/api.php")
    fun getPet(@Query("query") animal: String): Call<PetModel>

}