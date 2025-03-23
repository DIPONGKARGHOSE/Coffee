package com.example.coffee.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coffee.Adapter.SizeAdapter
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.Model.ItemsModel
import com.example.coffee.R
import com.example.coffee.databinding.ActivityDetailBinding


class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    private  lateinit var item:ItemsModel
    private  lateinit var  managment: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
         setContentView(binding.root)
        managment=ManagmentCart(this)
         bundle()
        initSizeList()

        }

    private fun initSizeList() {
        val sizeList =ArrayList<String>()
        sizeList.add("1")
        sizeList.add("2")
        sizeList.add("3")
        sizeList.add("4")

        binding.sizelist.adapter =SizeAdapter(sizeList)
        binding.sizelist.layoutManager=
            LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        val colorList =ArrayList<String>()
        for(imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        Glide.with(this)
            .load(colorList[0])
            .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
            .into(binding.imageView6)
    }

    private fun bundle() {
        binding.apply {
            item=intent.getParcelableExtra("object")!!
            titletxt.text=item.title
            descriptiontxt.text=item.description
            pricetxt.text="$" +item.price
            ratingBar2.rating=item.rating.toFloat()
            addtocartBtn.setOnClickListener {
                item.numberInCart=Integer.valueOf(
                    number.text.toString()
                )
                managment.insertItems(item)
            }
            back.setOnClickListener{
                startActivity(
                    Intent(
                        this@DetailActivity,MainpartActivity::class.java
                    )
                )
        }
            plus.setOnClickListener{
                number.text=(item.numberInCart+1).toString()
                item.numberInCart++
            }
            minus.setOnClickListener {
                if(item.numberInCart>0) {
                    number.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }


    }
}
}