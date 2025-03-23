package com.example.coffee.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffee.Adapter.CartAdpter
import com.example.coffee.Helper.ChangeNumberItemListener
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.R
import com.example.coffee.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    lateinit var  binding:ActivityCartBinding
    lateinit var  managmentCart: ManagmentCart
    private  var tax:Double =0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        managmentCart=ManagmentCart(this)
        calculateCart()
        initCartlist()
        setVariable()



    }


    private fun setVariable() {
        binding.back.setOnClickListener { finish() }
    }

    private fun initCartlist() {
        with(binding){
            CartView.layoutManager =LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
            CartView.adapter=CartAdpter(
                managmentCart.getListCart(),
                this@CartActivity,
                object:ChangeNumberItemListener{
                    override fun onChanged() {
                        calculateCart()
                    }
                }

            )
        }
    }

    private fun calculateCart() {
        val parcentTax=0.02
        val delivery=15
       // tax= (Math.round((managmentCart.getTotalFee()*parcentTax)*100)/100).toDouble()
       val total= (Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100).toDouble()
        val itemtotal =Math.round(managmentCart.getTotalFee()*100)/100
        binding.apply {
            subtotalprice.text="$$itemtotal"
           // totaltax.text="$$tax"
            deliveryprice.text="$$delivery"
            totalprice.text="$$total"



        }

    }
}