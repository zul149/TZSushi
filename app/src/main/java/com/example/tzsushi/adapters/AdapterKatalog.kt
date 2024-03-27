package com.example.tzsushi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tzsushi.MainActivity
import com.example.tzsushi.R
import com.example.tzsushi.databinding.ProductBinding
import com.example.tzsushi.model.Item



class AdapterKatalog(val context: MainActivity, val list: List<Item>): RecyclerView.Adapter<AdapterKatalog.ViewHolder>() {

    var click : OnClickListener? = null

    fun setOnClick (onClickListener: OnClickListener) {
        this.click = onClickListener
    }
    interface OnClickListener {
        fun onClick (position: Int, product: Item) {

        }
    }

    class ViewHolder( val binding: ProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product, parent, false)
        return ViewHolder(ProductBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //ссылаемся на элементы из layout
        val data: Item = list[position]
        holder.binding.itemListTitle.text = data.name
        holder.binding.itemListInfo.text = data.measure.toString() + " г"
        holder.binding.itemListPrice.text = data.price_current.toString() + " ₽"

        holder.binding.itemListPrice.setOnClickListener {
            holder.binding.itemListPrice.visibility = View.GONE
            holder.binding.llUpdateCartQuantity.visibility = View.VISIBLE
            holder.binding.cartQuantity.text = "1"
            context.changePrise(1, data)
        }

        holder.binding.ibAddCartItem.setOnClickListener {
            val count = holder.binding.cartQuantity.text.toString().toInt()
            holder.binding.cartQuantity.text = (count + 1).toString()
            context.changePrise(count + 1, data)
        }

        holder.binding.ibRemoveCartItem.setOnClickListener {
            val count = holder.binding.cartQuantity.text.toString().toInt()
            if (count > 0) {
                holder.binding.cartQuantity.text = (count - 1).toString()
                context.changePriseMinus(count - 1, data)
                if (count == 1) {
                    context.deleteProduct(data.id)
                    holder.binding.llUpdateCartQuantity.visibility = View.GONE
                    holder.binding.itemListPrice.visibility = View.VISIBLE
                }
            }
        }



        holder.itemView.setOnClickListener{
            if (click != null) {
                click!!.onClick(position, data)
            }
        }
    }

    override fun getItemCount(): Int { //подчсет количесвта элементов списка
        return list.size
    }


}