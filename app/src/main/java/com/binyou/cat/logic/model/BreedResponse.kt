package com.binyou.cat.logic.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Breed(val weight: Weight, val id: String, val name: String, val temperament: String, val origin: String, val description: String,
                 @SerializedName("life_span") val lifespan: String, @SerializedName("dog_friendly") val dogFriendly: Int,
                 @SerializedName("stranger_friendly") val strangerFriendly: Int, @SerializedName("child_friendly") val childFriendly: Int,
                 val intelligence: Int, val image: Image?, @SerializedName("wikipedia_url") val wikiUrl: String)

data class Weight(val imperial: String, val metric: String)

data class Image(val id: String?, val width: Int, val height: Int, val url: String? )