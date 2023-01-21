package com.example.mvvmapp.homePage.viewMdel

import android.app.Application
import android.graphics.ColorSpace
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmapp.data.BaseDomen
import com.example.mvvmapp.homePage.model.Model
import com.example.mvvmapp.homePage.repo.DriverRepository
import com.example.mvvmapp.unit.NetworkingStatus
import kotlinx.coroutines.launch

class DriverViewModel(
    private  val driverRepository: DriverRepository,
    app:Application
) :AndroidViewModel(app) {

    val _liveDriverAll = MutableLiveData<NetworkingStatus<List<Model>>>()
    val liveDriverAll : LiveData<NetworkingStatus<List<Model>>> = _liveDriverAll

    fun getAllDriver(){
        viewModelScope.launch {
            try {
                _liveDriverAll.postValue(NetworkingStatus.LOADING())
                val result = driverRepository.get_driver_all()
                if (result.isSuccessful){
                    val body= result.body()
                    val data = body?.data

                    if (data != null){
                        when(body.error_code){
                            BaseDomen.SUCCESS->{
                                _liveDriverAll.postValue(NetworkingStatus.SUCCESS(data))
                            }
                            BaseDomen.UnknownError->{
                                _liveDriverAll.postValue(NetworkingStatus.ERROR(data))
                            }
                        }
                    }else{
                        _liveDriverAll.postValue(NetworkingStatus.ERROR("data is null"))
                    }
                }else{
                    _liveDriverAll.postValue(NetworkingStatus.ERROR("Result is not successfull"))
                }
            }catch (e:Exception){
                _liveDriverAll.postValue(NetworkingStatus.ERROR(e.message ?:""))
            }
        }
    }
}