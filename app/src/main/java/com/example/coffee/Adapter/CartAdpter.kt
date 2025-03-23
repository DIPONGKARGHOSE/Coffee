package com.example.coffee.Adapter
import  android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.coffee.Activity.DetailActivity
import com.example.coffee.Helper.ChangeNumberItemListener
import com.example.coffee.Helper.ManagmentCart
import com.example.coffee.Model.ItemsModel
import com.example.coffee.databinding.ViewholderCartBinding



class CartAdpter(private val listItemSelected:ArrayList<ItemsModel>, context:Context, var changeNumberItemListener:ChangeNumberItemListener?= null): RecyclerView.Adapter<CartAdpter.Viewholder>() {
        class Viewholder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root)
    private  val managmentCart=ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdpter.Viewholder {
        val binding =ViewholderCartBinding.inflate(LayoutInflater. from (parent.context), parent,  false)
        return Viewholder (binding)
    }

    override fun onBindViewHolder(holder: CartAdpter.Viewholder, position: Int) {
        val items =listItemSelected[position]
        holder.binding.title.text=items.title
        holder.binding.price.text="$${items.price}"
        holder.binding.total.text="$${Math.round(items.numberInCart*items.price)}"
        holder.binding.digit.text=items.numberInCart.toString()

        Glide.with(holder.itemView.context)
            .load(items.picUrl[0])
            .apply(RequestOptions().transform(CenterCrop()))
            .into(holder.binding.imageView7)
        holder.itemView.setOnClickListener {
          managmentCart.plusItem(listItemSelected,position, object : ChangeNumberItemListener {
              override fun onChanged() {
                  notifyDataSetChanged()
                  changeNumberItemListener?.onChanged()
              }
          })

        }
        holder.binding.plustext.setOnClickListener {
            managmentCart.plusItem(listItemSelected,position, object : ChangeNumberItemListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })

        }
        holder.binding.minustxt.setOnClickListener {
            managmentCart.minusItem(listItemSelected,position, object : ChangeNumberItemListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemListener?.onChanged()
                }
            })

        }
        holder.binding.removeBtn.setOnClickListener{

        }

    }

    override fun getItemCount(): Int =listItemSelected.size

}