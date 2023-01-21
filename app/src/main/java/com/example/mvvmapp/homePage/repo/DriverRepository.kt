package com.example.mvvmapp.homePage.repo

import com.example.mvvmapp.data.Api

class DriverRepository(val server: Api) {
   suspend fun get_driver_all() = server.getDriver()
}