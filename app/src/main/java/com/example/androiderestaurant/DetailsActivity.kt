package com.example.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var plate: Plate
    private lateinit var cart: CartContainer

    private var count=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        binding=ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        plate = intent.getSerializableExtra("plate") as Plate
        getCart()

        binding.titlePlate.text=plate.name
        binding.pricePlate.text="0"
        Log.d("COUNT", count.toString())
        binding.amountPlate.text=count.toString()

        getTotalPrice()

        if (plate.images[0]!="")
        {
            Picasso.get().load(plate.images[0]).into(binding.imageView)
        }
        var listIngredient= ""
        for (i in plate.ingredients)
        {
            listIngredient += i.name_fr + ", "
        }
        binding.ingredientPlate.text=listIngredient

        binding.lessAmount.setOnClickListener()
        {
            removePlateFromCart()
        }

        binding.moreAmount.setOnClickListener()
        {
            addPlateToCart()
        }

        binding.cart.setOnClickListener()
        {
            updateCart()
        }



    }

    private fun addPlateToCart()
    {
        count++
        binding.amountPlate.text=count.toString()
        getTotalPrice()
    }

    private fun removePlateFromCart()
    {
        count--
        binding.amountPlate.text=count.toString()
        getTotalPrice()
    }

    private fun getTotalPrice()
    {
        var totalPrice= 0.0
        totalPrice += count * plate.prices[0].price.toFloat()
        binding.pricePlate.text = totalPrice.toString()
    }

    private fun updateCart()
    {
        var found = false
        for (item in cart.CartsObjectList)
        {
            if(item.plate == plate) {
                val index = cart.CartsObjectList.indexOf(item)
                cart.CartsObjectList[index].amountPlate=count
                found= true
            }
        }
        if (!found)
            cart.CartsObjectList.add(CartObject(plate, count))
        cart.displayObject()
        saveCart()
    }

    fun saveCart()
    {
        val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        val jsonTutPretty: String = gsonPretty.toJson(cart)

        File(cacheDir.absolutePath,"finalCart.json").writeText(jsonTutPretty)
    }

    private fun getCart()
    {
        var file = File(cacheDir.absolutePath,"finalCart.json")

        if( file.exists()) {
            val output = file.readText()
            val gson = Gson()

            val result= gson.fromJson(output, CartContainer::class.java)

            cart=result


            var index = cart.CartsObjectList.map { it.plate.name }.indexOf(plate.name)
            Log.d("INDEX",index.toString())
            if (index >= 0)
            {
                count = cart.CartsObjectList[index].amountPlate
                Log.d("COUNT CART", count.toString())
            }

        }
        else
            cart= CartContainer(mutableListOf())

        cart.displayObject()
    }
}