package com.binyou.cat.ui.breed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.binyou.cat.logic.Repository
import com.binyou.cat.logic.model.Breed

class BreedViewModel : ViewModel() {

    val breedList = ArrayList<Breed>()

    val breedLiveData = Repository.searchBreeds()
}