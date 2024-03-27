package com.example.tzsushi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.tzsushi.adapters.AdapterKatalog
import com.example.tzsushi.databinding.ActivityMainBinding
import com.example.tzsushi.fragments.MenuBottomSheetFragment
import com.example.tzsushi.model.Item
import com.example.tzsushi.model.ItemsAPI
import com.example.tzsushi.model.Root
import com.google.android.material.tabs.TabLayout
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import java.io.Serializable
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private  var hashMap = HashMap<String, Any>()
    private  var sum = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bag.setOnClickListener {
            for ((key, value) in hashMap) {
            var x = value as Item
            var price = x.price_current * x.measure
            sum += price
        }
            if (sum > 0) {
                val intent = Intent(this@MainActivity, ShoppingBagActivity::class.java)
                intent.putExtra("hashMap", hashMap)
                intent.putExtra("sum", sum)
                startActivity(intent)
            } else {
            val intent = Intent(this@MainActivity, ShoppingBagActivity::class.java)
            startActivity(intent)
        }


        }

        binding.menu.setOnClickListener {
            val bottomSheetFragment = MenuBottomSheetFragment()
            // Показываем BottomSheet
            MenuBottomSheetFragment().show(supportFragmentManager, bottomSheetFragment.tag)
        }

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.ViewPager2)

        tabLayout.addTab(tabLayout.newTab().setText("Роллы"))
        tabLayout.addTab(tabLayout.newTab().setText("Суши"))
        tabLayout.addTab(tabLayout.newTab().setText("Наборы"))
        tabLayout.addTab(tabLayout.newTab().setText("Горячие блюда"))
        tabLayout.addTab(tabLayout.newTab().setText("Напитки"))
        tabLayout.addTab(tabLayout.newTab().setText("Соусы"))
        tabLayout.addTab(tabLayout.newTab().setText("Салаты"))



        getProducts()

    }


    private fun getProducts() {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            //.baseUrl("https://drive.google.com/file/d/")
            .baseUrl("https://drive.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build() // вызов классa в качестве gson

        val retrofitAPI = retrofit.create(ItemsAPI::class.java)

        val call: Call<Root> =
            retrofitAPI.getItemsById("128hUGBg3biXOTmhbvx0xkYS-4sYye8HL") // экзeмпляр для выполнеия запроса получающий путь к окончательной ссылке

        Log.d("TAGGGGG", "retrofitAPI.getItemsById")

        call.enqueue(object : Callback<Root?> {
            override fun onResponse(
                call: Call<Root?>,
                response: Response<Root?>
            ) {

                val list: List<Item>? = response.body()

                Log.d("MyTag", "onResponse")

                val activity = this@MainActivity
                binding.spisok.layoutManager =
                    GridLayoutManager(activity, 2) //GridLayoutManager - сетка
                binding.spisok.setHasFixedSize(true) // не будет искажаться, когда будет изменяться количество элементов
                val adapterKatalog =
                    list?.let { AdapterKatalog(activity, it) } // x содержить значиения адапетра
                binding.spisok.adapter = adapterKatalog

                adapterKatalog?.setOnClick(object : AdapterKatalog.OnClickListener {
                    override fun onClick(position: Int, item: Item) {
                        super.onClick(position, item)
                        val intent = Intent(this@MainActivity, ProductActivity::class.java)
                        intent.putExtra("item", item)
                        startActivity(intent)
                    }
                })
                binding.search.setOnClickListener {
                    val intent = Intent(this@MainActivity, SearchActivity::class.java)
                    intent.putExtra("search", list as Serializable)
                    startActivity(intent)

                }

            }


            override fun onFailure(call: Call<Root?>, t: Throwable) {
                Log.d("MyTag", "onFailure")
                Log.d("MyTag", t.message.toString())
            }

        })
    }

    fun changePrise(i: Int, data: Item) {
        data.measure = i //количество
        hashMap[data.id.toString()] = data
        for ((key, value) in hashMap) {

            var x = value as Item
            var price = x.price_current * x.measure
            sum += price
        }
        binding.bag.text = sum.toString() + " ₽"
        sum = 0
    }

    fun changePriseMinus(i: Int, data: Item) {
        data.measure = i //количество
        hashMap[data.id.toString()] = data
        for ((key, value) in hashMap) {

            var x = value as Item
            var price = x.price_current * x.measure
            sum += price
        }
        binding.bag.text = sum.toString() + " ₽"
        sum = 0
    }

    fun deleteProduct(id: Int) {
        hashMap.remove(id.toString())
    }


}