package com.binyou.cat.logic.network

import com.binyou.cat.logic.model.Breed
import retrofit2.Call
import retrofit2.http.GET

interface BreedService {
    @GET("v1/breeds")
    fun searchBreeds() : Call<List<Breed>>
}