package com.example.tzsushi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tzsushi.databinding.ActivityProductBinding
import com.example.tzsushi.model.Item

class ProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductBinding
    private lateinit var product : Item
    private  var hashMap = HashMap<String, Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("item")) {
            product = intent.getParcelableExtra("item")!! //прием заполненного объекта
        }

        binding.itemListTitle.text = product.name
        binding.description.text = product.description
        binding.measureTotal.text = product.measure.toString() + " г"
        binding.energyTotal.text = product.energy_per_100_grams.toString() + " ккал"
        binding.proteinsTotal.text = product.proteins_per_100_grams.toString() + " г"
        binding.fatsTotal.text = product.fats_per_100_grams.toString() + " г"
        binding.carbohydratesTotal.text = product.carbohydrates_per_100_grams.toString() + " г"
        binding.buttonPrice.text = product.price_current.toString() + " ₽"


        binding.buttonPrice.setOnClickListener{
            val Addproduct = product
            Addproduct.measure = 1
            hashMap[product.id.toString()] = Addproduct
            val intent = Intent(this@ProductActivity, ShoppingBagActivity::class.java)
            intent.putExtra("hashMap", hashMap)
            startActivity(intent)
            Toast.makeText(this@ProductActivity, "Продукт был добавлен в корзину", Toast.LENGTH_SHORT).show()
        }



        binding.back.setOnClickListener {
            val intent = Intent(this@ProductActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }


}