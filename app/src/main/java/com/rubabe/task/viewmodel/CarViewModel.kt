package com.rubabe.task.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rubabe.task.Constants
import com.rubabe.task.api.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarViewModel: ViewModel() {
    lateinit var api: Api
    var carLiveData = MutableLiveData<DTOResult>()
    var carManufacturerLiveData = MutableLiveData<GetManufacturerDetailsResults>()

    fun getCar(context : Context, json: String) {
        api = Constants.getApi()
        api.getCarData(json).enqueue(object: Callback<DTOResult> {
            override fun onResponse(call: Call<DTOResult>, response: Response<DTOResult>) {
                val data: DTOResult? = response.body()
                this@CarViewModel.carLiveData.postValue(data)
            }

            override fun onFailure(call: Call<DTOResult>, t: Throwable) {
                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
            }

        })

    }


    fun getManufacturerDetails(context: Context, carType: String, json: String){
        api = Constants.getApi()
        api.getGetManufacturerDetails(carType, json).enqueue(object: Callback<GetManufacturerDetailsResults> {
            override fun onResponse(call: Call<GetManufacturerDetailsResults>, response: Response<GetManufacturerDetailsResults>) {
                val data: GetManufacturerDetailsResults? = response.body()
                this@CarViewModel.carManufacturerLiveData.postValue(data)
            }

            override fun onFailure(call: Call<GetManufacturerDetailsResults>, t: Throwable) {
                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG).show()
            }

        })

    }
}