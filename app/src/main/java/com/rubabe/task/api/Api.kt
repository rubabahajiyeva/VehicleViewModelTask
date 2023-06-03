package com.rubabe.task.api


import com.rubabe.task.model.DTOResult
import com.rubabe.task.model.GetManufacturerDetailsResults
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("getallmanufacturers")
    fun getCarData(@Query("format") format: String): Call<DTOResult>

    @GET("GetManufacturerDetails/{manufacturer}")
    fun getGetManufacturerDetails(
        @Path("manufacturer") manufacturer: String,
        @Query("format") format: String
    ): Call<GetManufacturerDetailsResults>

    @GET("GetMakeForManufacturer/{manufacturer}")
    fun getMakeForManufacturer(
        @Path("manufacturer") manufacturer: String,
        @Query("format") format: String): Call<GetManufacturerDetailsResults>
}