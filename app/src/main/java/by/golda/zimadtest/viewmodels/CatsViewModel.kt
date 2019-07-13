package by.golda.zimadtest.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import by.golda.zimadtest.network.Status
import by.golda.zimadtest.providers.CatsProvider

class CatsViewModel: ViewModel() {

    var status: MutableLiveData<Status> = MutableLiveData()

    fun getCats() = CatsProvider.instance.getCats(status)

}