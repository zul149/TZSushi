package com.example.tzsushi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tzsushi.ShoppingBagActivity
import com.example.tzsushi.databinding.ShoppingBagBinding
import com.example.tzsushi.model.Item

class AdapterShoppingBag(val activity: ShoppingBagActivity, val list: ArrayList<Item>): RecyclerView.Adapter<AdapterShoppingBag.ViewHolder>() {
    class ViewHolder( val binding: ShoppingBagBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShoppingBagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: Item = list[position]
        holder.binding.cartItemTitle.text = data.name
        holder.binding.cartItemPrice.text = (data.price_current * data.measure).toString()
        holder.binding.cartQuantity.text = data.measure.toString()
        var count = Integer.parseInt(holder.binding.cartQuantity.text.toString())

        holder.binding.cartQuantity.setOnClickListener {
            holder.binding.cartQuantity.text = "1"
//            activity.changePrise(1, data)
        }

        holder.binding.ibAddCartItem.setOnClickListener {
            holder.binding.cartQuantity.text = (count!! + 1).toString()
            count = count?.plus(1)!!
            var sum = count!! * data.price_current
            holder.binding.cartItemPrice.text = sum.toString()
            activity.changePrise(count, data)
        }

        holder.binding.ibRemoveCartItem.setOnClickListener {
            if (count!! > 0) {
                holder.binding.cartQuantity.text = (count!! - 1).toString()
                count = count?.minus(1)!!
                var sum = count!! * data.price_current
                holder.binding.cartItemPrice.text = sum.toString()
                activity.changePriseMinus(count, data)
                if (count == 0) {
                    activity.deleteProduct(data.id)

                }
            }
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }
}