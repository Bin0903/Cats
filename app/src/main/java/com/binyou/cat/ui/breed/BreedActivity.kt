package com.binyou.cat.ui.breed

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.binyou.cat.R
import com.binyou.cat.logic.model.Breed
import com.binyou.cat.logic.model.Image
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_breed.*

class BreedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breed)

        val rawData = intent.getStringExtra("breed")
        val breed = Gson().fromJson(rawData, Breed::class.java)
        updateView(breed)
    }

    @SuppressLint("SetTextI18n")
    private fun updateView(breed: Breed) {
        ImageLoadTask(breedImage).execute(breed.image)

        name.text = "Breed Name: " + breed.name
        temperament.text = "Temperament:\n" + breed.temperament
        origin.text = "Origin: " + breed.origin
        description.text = "Description:\n" + breed.description
        lifespan.text = "Lifespan: " + breed.lifespan
        intelligence.text = "Intelligence Index - " + breed.intelligence.toString()
        dogFriendly.text = "Dog Friendly Index - " + breed.dogFriendly.toString()
        childFriendly.text = "Child Friendly Index - " + breed.childFriendly.toString()
        strangerFriendly.text = "Stranger Friendly Index - " + breed.strangerFriendly.toString()
        wikiUrl.text = "Wiki:\n" + breed.wikiUrl
    }

    @SuppressLint("StaticFieldLeak")
    inner class ImageLoadTask(private val imageView: ImageView): AsyncTask<Image, Unit, Bitmap>(){

        override fun doInBackground(vararg params: Image): Bitmap {
            Log.d("Breed", params[0].toString())
            return params[0].let {
                Glide.with(this@BreedActivity)
                        .asBitmap()
                        .load(it.url)
                        .submit(it.width, it.height)
                        .get()
            }
        }

        override fun onPostExecute(result: Bitmap) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }
    }
}