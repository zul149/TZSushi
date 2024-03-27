package com.example.tzsushi.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tzsushi.SearchActivity
import com.example.tzsushi.databinding.ProductBinding
import com.example.tzsushi.model.Item

class SearchAdapter(val context: SearchActivity, val list: MutableList<Item>?)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder( val binding: ProductBinding) : RecyclerView.ViewHolder(binding.root) // привязали акктивити xml
    var click : OnClickListener? = null

    fun setOnClick (onClickListener: OnClickListener) {
        this.click = onClickListener
    }
    interface OnClickListener {
        fun onClick (position: Int, data: Item) {

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.ViewHolder {
        val binding = ProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { //ссылаемся на элементы из layout
        val data: Item = list!![position]
   //     holder.binding.text = model.name
        holder.binding.itemListTitle.text = data.name
        holder.binding.itemListInfo.text = data.measure.toString() + " г"
        holder.binding.itemListPrice.text = data.price_current.toString() + " ₽"

        holder.itemView.setOnClickListener{
            if (click != null) {
                click!!.onClick(position, data)
            }
        }

    }

    override fun getItemCount(): Int {
        return list!!.size
    }




}