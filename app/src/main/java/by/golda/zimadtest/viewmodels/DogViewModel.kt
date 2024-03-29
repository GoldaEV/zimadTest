package by.golda.zimadtest.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import by.golda.zimadtest.network.Status
import by.golda.zimadtest.providers.PetProvider

class DogViewModel: ViewModel() {

    var status: MutableLiveData<Status> = MutableLiveData()

    fun getPet(petType: String) = PetProvider.instance.getPat(petType, status)

}