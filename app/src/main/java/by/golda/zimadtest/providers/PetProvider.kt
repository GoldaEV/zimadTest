package by.golda.zimadtest.providers

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import by.golda.zimadtest.model.PetModel
import by.golda.zimadtest.network.Api
import by.golda.zimadtest.network.ServiceRetrofit
import by.golda.zimadtest.network.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PetProvider {

    private var data: MutableLiveData<PetModel> = MutableLiveData()

    companion object Singleton {
        val instance: PetProvider by lazy { PetProvider() }
    }

    fun getCats(petString: String, status: MutableLiveData<Status>): LiveData<PetModel> {
            status.postValue(Status.Loading)

            ServiceRetrofit.createService(Api::class.java).getPet(petString).enqueue(object : Callback<PetModel> {
                override fun onFailure(call: Call<PetModel>, t: Throwable) {
                    status.postValue(Status.Failure)
                }

                override fun onResponse(call: Call<PetModel>, response: Response<PetModel>) {
                    data.postValue(response.body())
                    status.postValue(Status.Loading)
                }
            })
        return data
    }









}