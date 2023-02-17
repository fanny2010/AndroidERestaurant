package com.example.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.androiderestaurant.databinding.ActivityMenuListBinding
import com.example.androiderestaurant.network.MenuResult
import com.example.androiderestaurant.network.NetworkConstants
import com.google.gson.GsonBuilder
import org.json.JSONObject

enum class Category { STARTER, MAIN, DESSERT }
class MenuListActivity : AppCompatActivity() {

    companion object {
        val extraKey = "extraKey"
    }

    lateinit var binding: ActivityMenuListBinding
    lateinit var currentCategory: Category
    private lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getSerializableExtra(extraKey) as? Category
        currentCategory = category ?: Category.STARTER
        supportActionBar?.title = categoryName()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CustomAdapter(listOf()) {
            goToDetails(it)
        }
        makeRequest()
    }

    private fun showDatas(category: Category) {}
    private fun makeRequest() {
        val params = JSONObject()
        params.put(NetworkConstants.idShopKey, 1)
        val queue = Volley.newRequestQueue(this)
        val request = JsonObjectRequest(
            Method.POST,
            NetworkConstants.url,
            params,
            { result ->
                Log.d("request", result.toString(2))
                parseData(result.toString())
                val dataFromJSON = GsonBuilder().create().fromJson(result.toString(), MenuResult::class.java)
                val categoryFiltered = dataFromJSON.data.firstOrNull() { it.name == categoryFilterKey() }
                adapter=binding.recyclerView.adapter as CustomAdapter
                adapter.updateData(categoryFiltered)
            },
            { error ->
                Log.e("request", error.toString())
            })
        queue.add(request)
    }

    private fun parseData(data: String) {
        val result = GsonBuilder().create().fromJson(data, MenuResult::class.java)
        val category = result.data.first {it.name == categoryFilterkey() }
           showDatas(category)
    }

    private fun showDatas(category: com.example.androiderestaurant.network.Category) {
        binding.RecyclerView.layoutManager = LinearLayoutManager(this)
        binding.RecyclerView.adapter = CustomAdapter(listOf("1", "2", "3")) {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun categoryName(): String {
        return when (currentCategory) {
            Category.STARTER -> getString(R.string.starters)
            Category.MAIN -> getString(R.string.main)
            Category.DESSERT -> getString(R.string.finish)
        }
    }
    private fun categoryFilterkey(): String {
        return when (currentCategory) {
            Category.STARTER -> "Entrées"
            Category.MAIN -> "Plats"
            Category.DESSERT -> "Desserts"
}
    }
    private fun goToDetails(plate: Plate)
    {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("plate",plate)
        startActivity(intent)
    }
    override fun onStart() {
        super.onStart()
        Log.d("lifeCycle", "MainActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifeCycle", "MenuListActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifeCycle", "MainActivity onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifeCycle", "MainActivity onDestroy")
    }
}