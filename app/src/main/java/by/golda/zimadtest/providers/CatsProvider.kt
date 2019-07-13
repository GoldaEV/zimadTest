package by.golda.zimadtest.providers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import by.golda.azsmapnext.network.Api
import by.golda.azsmapnext.network.ServiceRetrofit
import by.golda.zimadtest.model.CatModel
import by.golda.zimadtest.model.Data
import by.golda.zimadtest.network.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatsProvider {

    private var data: MutableLiveData<CatModel> = MutableLiveData()

    companion object Singleton {
        val instance: CatsProvider by lazy { CatsProvider() }

    }

    private val animal = "cat"

    fun getCats(status: MutableLiveData<Status>): LiveData<CatModel> {

            status.postValue(Status.Loading)

            ServiceRetrofit.createService(Api::class.java).getCats(animal).enqueue(object : Callback<CatModel> {
                override fun onFailure(call: Call<CatModel>, t: Throwable) {
                    status.postValue(Status.Failure)
                }

                override fun onResponse(call: Call<CatModel>, response: Response<CatModel>) {
                    data.postValue(response.body())
                    status.postValue(Status.Loading)
                }

            })

        return data
    }









}