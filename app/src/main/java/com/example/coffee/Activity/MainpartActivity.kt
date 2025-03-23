package com.example.coffee.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.Adapter.CategoryAdapter
import com.example.coffee.Adapter.OfferAdapter
import com.example.coffee.Adapter.PopularAdapter
import com.example.coffee.R
import com.example.coffee.ViewModel.MainViewModel
import com.example.coffee.databinding.ActivityMainpartBinding

class MainpartActivity : AppCompatActivity() {
    lateinit var binding :ActivityMainpartBinding
    private val  viewModel= MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainpartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initCategory()
        initPopular()
        initOffer()
        bottomMenu()
    }

    private fun bottomMenu() {
        binding.fab.setOnClickListener{startActivity(Intent(this,CartActivity::class.java))}
    }

    private fun initOffer() {
        binding.progressBaroffer.visibility=View.VISIBLE
        viewModel.offer.observe(this, Observer {
            binding.recyclerViewoffer.layoutManager=
                LinearLayoutManager(
                    this@MainpartActivity,
                    LinearLayoutManager.HORIZONTAL,false
                )
            binding.recyclerViewoffer.adapter =OfferAdapter(it)
            binding.progressBaroffer.visibility=View.GONE
        })
        viewModel.loadoffer()
    }

    private fun initPopular() {
        binding.progressBarpopular.visibility=View.VISIBLE
        viewModel.popular.observe(this, Observer {
            binding.recyclerViewpopular.layoutManager=
                LinearLayoutManager(
                    this@MainpartActivity,
                    LinearLayoutManager.HORIZONTAL,false
                )
            binding.recyclerViewpopular.adapter =PopularAdapter(it)
            binding.progressBarpopular.visibility=View.GONE
        })
        viewModel.loadpopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this, Observer {
            binding.recyclerViewCategory.layoutManager=
                LinearLayoutManager(
                    this@MainpartActivity,
                    LinearLayoutManager.HORIZONTAL,false
                )
            binding.recyclerViewCategory.adapter=CategoryAdapter(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()
    }
}