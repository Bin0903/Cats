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

    private fun updateView(breed: Breed) {
        ImageLoadTask(breedImage).execute(breed.image)

        name.text = breed.name
        temperament.text = breed.temperament
        origin.text = breed.origin
        description.text = breed.description
        lifespan.text = breed.lifespan
        intelligence.text = breed.intelligence.toString()
        dogFriendly.text = breed.dogFriendly.toString()
        childFriendly.text = breed.childFriendly.toString()
        strangerFriendly.text = breed.strangerFriendly.toString()
        wikiUrl.text = breed.wikiUrl
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