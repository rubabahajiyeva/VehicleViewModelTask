package com.rubabe.task

import com.rubabe.task.api.Api
import com.rubabe.task.network.RetrofitClient

class Constants {
    companion object{
        val BASE_URL = "https://vpic.nhtsa.dot.gov/api/vehicles/"
        var FORMAT = "json"
        fun getApi(): Api {
            return RetrofitClient.getClient(BASE_URL).create(Api::class.java)
        }
    }
}