package com.binyou.cat.ui.breed

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_MAIN
import android.graphics.Bitmap
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binyou.cat.R
import com.binyou.cat.logic.model.Breed
import com.binyou.cat.logic.model.Image
import com.bumptech.glide.Glide
import com.google.gson.Gson

class BreedAdapter(private val activity: Activity, private val breedList: List<Breed>) :
    RecyclerView.Adapter<BreedAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val breedName: TextView = view.findViewById(R.id.breedName)
        val breedDescription: TextView = view.findViewById(R.id.description)
        val breedPicture: ImageView = view.findViewById(R.id.breedPicture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.breed_item,
            parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            val breed = breedList[position]
            val intent = Intent(parent.context, BreedActivity::class.java).apply {
                putExtra("breed", Gson().toJson(breed))
            }
            activity.startActivity(intent)
        }
        return holder
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val breed = breedList[position]
        holder.breedName.text = breed.name
        holder.breedDescription.text = breed.description

        ImageLoadTask(holder.breedPicture).execute(breedList[position].image)
    }

    override fun getItemCount() = breedList.size

    @SuppressLint("StaticFieldLeak")
    inner class ImageLoadTask(private val imageView: ImageView): AsyncTask<Image, Unit, Bitmap>(){

        override fun doInBackground(vararg params: Image): Bitmap {
            Log.d("Breed", params[0].toString())
            return Glide.with(activity)
                .asBitmap()
                .load(params[0].url)
                .submit(200, 200)
                .get()
        }

        override fun onPostExecute(result: Bitmap) {
            super.onPostExecute(result)
            imageView.setImageBitmap(result)
        }
    }
}

