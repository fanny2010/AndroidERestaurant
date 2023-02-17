package com.example.androiderestaurant.network

import android.util.Log

class CartContainer (
    var CartsObjectList: MutableList<CartObject>
        ){
    fun displayObject() {
        var stringList=""
        for (i in CartsObjectList)
        {
            stringList+="plat n°"+ CartsObjectList.indexOf(i)+ " name = "+ i.plate.name + ", quantity = " + i.amountPlate.toString()+ "\n"
        }
        Log.d("CART OBJECT", stringList)
    }
}