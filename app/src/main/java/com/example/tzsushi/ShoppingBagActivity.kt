package com.example.tzsushi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tzsushi.adapters.AdapterShoppingBag
import com.example.tzsushi.databinding.ActivityShoppingBagBinding
import com.example.tzsushi.model.Item



class ShoppingBagActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBagBinding
    private var hashMap = HashMap<String, Any>()
    private var arrayList = ArrayList<Item>()
    private  var sum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShoppingBagBinding.inflate(layoutInflater)
        setContentView(binding.root)



        if (intent.hasExtra("hashMap")) {
            hashMap = intent.getSerializableExtra("hashMap")!! as HashMap<String, Any>
            for ((element, value) in hashMap) { //value - объект
                val item = value as Item
                arrayList.add(item)
            }
            binding.buttonPrice.text = "Заказать за " + arrayList[0].price_current + " ₽"
        }

        if (intent.hasExtra("sum")) {
            sum = intent.getSerializableExtra("sum") as Int
            binding.buttonPrice.text = "Заказать за " + sum.toString() + " ₽"
        }

        if (arrayList.size > 0) {
            binding.text.visibility = View.GONE

            binding.shoppingBagItemsList.layoutManager = LinearLayoutManager(this)

            binding.shoppingBagItemsList.setHasFixedSize(true) // не будет искажаться, когда будет изменяться количество элементов
            val adapterShoppingBag =
                AdapterShoppingBag(this, arrayList) // x содержить значиения адапетра
            binding.shoppingBagItemsList.adapter = adapterShoppingBag

        } else {
            binding.shoppingBagItemsList.visibility = View.GONE
            binding.checkout.visibility = View.GONE
            binding.text.visibility = View.VISIBLE
        }

        setupActionBar()
    }


    private fun setupActionBar() { // toolbar
        setSupportActionBar(binding.toolbarShoppingBag)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.arrow)

        }
        binding.toolbarShoppingBag.setNavigationOnClickListener { onBackPressed() }
    }

    fun updateProducts() {
        for ((element, value) in hashMap) { //value - объект
            val item = value as Item
            arrayList.add(item)
        }
        if (arrayList.size > 0) {
            binding.text.visibility = View.GONE

            binding.shoppingBagItemsList.layoutManager = LinearLayoutManager(this)

            binding.shoppingBagItemsList.setHasFixedSize(true) // не будет искажаться, когда будет изменяться количество элементов
            val adapterShoppingBag =
                AdapterShoppingBag(this, arrayList) // x содержить значиения адапетра
            binding.shoppingBagItemsList.adapter = adapterShoppingBag

        } else {
            binding.shoppingBagItemsList.visibility = View.GONE
            binding.checkout.visibility = View.GONE
            binding.text.visibility = View.VISIBLE
        }
    }

    fun deleteProduct(id: Int) {
        hashMap.remove(id.toString())
        val x = hashMap.size
        arrayList = ArrayList()
        updateProducts()
    }

    fun changePrise(count: Int, data: Item) {
        data.measure = count //количество
        hashMap[data.id.toString()] = data
        sum = 0
        for ((key, value) in hashMap) {

            var x = value as Item
            var price = x.price_current * x.measure
            sum += price
        }
        binding.buttonPrice.text = sum.toString() + " ₽"
        arrayList = ArrayList()
        updateProducts()
    }

    fun changePriseMinus(count: Int, data: Item) {
        data.measure = count //количество
        hashMap[data.id.toString()] = data
        sum = 0
        for ((key, value) in hashMap) {

            var x = value as Item
            var price = x.price_current * x.measure
            sum += price
        }
        binding.buttonPrice.text = sum.toString() + " ₽"
        arrayList = ArrayList()
        updateProducts()
    }



}