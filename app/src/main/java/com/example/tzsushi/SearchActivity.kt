package com.example.tzsushi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tzsushi.adapters.AdapterKatalog
import com.example.tzsushi.adapters.SearchAdapter
import com.example.tzsushi.databinding.ActivitySearchBinding
import com.example.tzsushi.model.Item
import java.util.Locale

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    var list: MutableList<Item>? = mutableListOf()
    var listMeals: MutableList<Item>? = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("search")) {
            list = intent.getSerializableExtra("search")!! as MutableList<Item>
        }


        // binding.btnOk.isVisible = false
        binding.searchView.setOnClickListener {
            binding.btnOk.isVisible = true

        }
        binding.btnOk.setOnClickListener {
            listMeals= mutableListOf()
            var meal = binding.searchView.query.toString()
            if (list!!.size > 0) {
                for (element in list!!) {
                    if (element.name.lowercase(Locale.getDefault())
                            .contains(meal.lowercase(Locale.getDefault()))
                    ) {
                        listMeals!!.add(element)

                    }
                }
                updateList()
            }


        }
        setSupportActionBar(binding.toolbarSearch)

        // Настройка обработчика нажатия кнопки "назад"
        binding.btnBack.setOnClickListener {
            onBackPressed() // При нажатии на кнопку "назад" осуществляется переход в предыдущую активность
        }
    }
        fun updateList() {
            if (listMeals!!.size > 0) {
                binding.text.visibility = View.GONE
                binding.textNotFound.visibility = View.GONE
                binding.searchItemsList.visibility = View.VISIBLE

                binding.searchItemsList.layoutManager = GridLayoutManager(this, 2)

                binding.searchItemsList.setHasFixedSize(true) // не будет искажаться, когда будет изменяться количество элементов
                val adapterSearch =
                    SearchAdapter(this, listMeals) // x содержить значиения адапетра
                binding.searchItemsList.adapter = adapterSearch
                adapterSearch?.setOnClick(object : SearchAdapter.OnClickListener {
                    override fun onClick(position: Int, item: Item) {
                        super.onClick(position, item)
                        val intent = Intent(this@SearchActivity, ProductActivity::class.java)
                        intent.putExtra("item", item)
                        startActivity(intent)
                    }
                })
            }
            else{
                binding.textNotFound.visibility = View.VISIBLE
                binding.text.visibility = View.GONE
                binding.searchItemsList.visibility = View.GONE


            }
        }
    }







