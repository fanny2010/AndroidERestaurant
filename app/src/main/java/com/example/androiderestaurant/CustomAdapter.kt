package com.example.androiderestaurant

import android.content.DialogInterface.OnClickListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androiderestaurant.databinding.CellCustomBinding
import kotlin.math.log

class CustomAdapter(val items: List<String>, val clickListener: (Int) -> Unit): RecyclerView.Adapter<CustomAdapter.CellViewHolder>() {
    class CellViewHolder(binding: CellCustomBinding) : RecyclerView.ViewHolder(binding.root) {
    val textView: TextView = binding.itemName
        val root: ConstraintLayout = binding.root
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
       val binding = CellCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CellViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.textView.text = items[position]
        holder.root.setOnClickListener{
            Log.d("click", "click on ${position}")
            clickListener(position)
        }
    }
}