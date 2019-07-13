package by.golda.azsmapnext.network

import by.golda.zimadtest.model.CatModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("http://kot3.com/xim/api.php?query=cat")
    fun getCats(@Query("query") animal: String): Call<CatModel>

}