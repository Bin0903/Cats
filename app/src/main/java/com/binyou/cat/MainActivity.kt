package com.binyou.cat

import android.graphics.Bitmap
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.binyou.cat.logic.model.Breed
import com.binyou.cat.ui.breed.BreedAdapter
import com.binyou.cat.ui.breed.BreedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val viewModel by lazy {ViewModelProvider(this).get(BreedViewModel::class.java) }
    private lateinit var adapter: BreedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager
        adapter = BreedAdapter(this, viewModel.breedList)
        recyclerView.adapter= adapter

        refreshBreeds()

        swipeRefresh.setColorSchemeResources(R.color.design_default_color_primary)
        swipeRefresh.setOnRefreshListener {
            refreshBreeds()
        }
    }

    private fun refreshBreeds() {
        viewModel.breedLiveData.observe(this, Observer { result ->
            val breeds = result.getOrNull()

            if (breeds != null) {
                recyclerView.visibility = View.VISIBLE
                viewModel.breedList.clear()
                viewModel.breedList.addAll(breeds)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "未能查询到猫的物种信息", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })

        swipeRefresh.isRefreshing = false
    }
}