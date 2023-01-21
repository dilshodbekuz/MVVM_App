package com.example.mvvmapp.homePage.viewMdel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.homePage.repo.DriverRepository

class DriverViewModelFactory(
    val application: Application,
    private val repository: DriverRepository,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DriverViewModel::class.java)) {
                return DriverViewModel(repository, application) as T
            }
            throw IllegalAccessException()
        }
}
