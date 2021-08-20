package com.binyou.cat.logic

import androidx.lifecycle.liveData
import com.binyou.cat.logic.model.Breed
import com.binyou.cat.logic.network.CatNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

object Repository {

    fun searchBreeds() = liveData(Dispatchers.IO) {
        val result = try {
            var breeds = CatNetwork.searchBreeds()
            breeds = breeds.filter { it.image?.url != null }
            Result.success(breeds)
        } catch (e: Exception) {
            Result.failure<List<Breed>>(e)
        }
        emit(result)
    }
}