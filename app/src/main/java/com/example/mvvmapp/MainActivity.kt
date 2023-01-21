package com.example.mvvmapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmapp.data.RetrofitClient
import com.example.mvvmapp.databinding.ActivityMainBinding
import com.example.mvvmapp.homePage.adapter.DriverAdapter
import com.example.mvvmapp.homePage.model.Model
import com.example.mvvmapp.homePage.repo.DriverRepository
import com.example.mvvmapp.homePage.viewMdel.DriverViewModel
import com.example.mvvmapp.homePage.viewMdel.DriverViewModelFactory
import com.example.mvvmapp.unit.NetworkingStatus

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: DriverViewModel
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = RetrofitClient.instance
        viewModel = ViewModelProvider(
            this,
            DriverViewModelFactory(this.application, DriverRepository(api)
            )
        )[DriverViewModel::class.java]

        viewModel.getAllDriver()

        observbel()
    }



    val liveAllDriver = Observer<NetworkingStatus<List<Model>>>{
        when(it){
            is NetworkingStatus.LOADING->{
                binding.progressCircular.visibility = View.VISIBLE
            }
            is NetworkingStatus.SUCCESS->{
                binding.recId.adapter = DriverAdapter(it.data)
                binding.progressCircular.visibility = View.GONE
            }
            is NetworkingStatus.ERROR->{
                binding.progressCircular.visibility = View.GONE
            }
        }
    }

    private fun observbel() {
        viewModel.liveDriverAll.observe(this,liveAllDriver)
    }

}