package com.example.androiderestaurant

import android.content.DialogInterface.OnClickListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.androiderestaurant.databinding.CellCustomBinding
import com.example.androiderestaurant.network.Plate
import com.squareup.picasso.Picasso
import kotlin.math.log

class CustomAdapter(val items: List<String>, val clickListener: (Int) -> Unit): RecyclerView.Adapter<CustomAdapter.CellViewHolder>() {
    class CellViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.itemName)
        val priceTextView : TextView= itemView.findViewById(R.id.priceTextView)
        val imageView: ImageView = itemView.findViewById(R.id.imagePlat)
        val card : ConstraintLayout= itemView.findViewById(R.id.CardLinear)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
       val binding = LayoutInflater.from(parent.context).inflate(R.layout.cell_custom,parent,false)
        return CellViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val plate = items[position]
        holder.textView.text = plate.name
        holder.priceTextView.text = plate.prices.first().price + " €"
        Picasso.get().load(getThumbnail(plate)).into(holder.imageView)
        holder.card.setOnClickListener{
            Log.d("click", "click on ${position}")
            clickListener(plate)
        }
    }
    public fun updateData(filter: Category?)
    {
        if(filter != null)
        {
            this.items =filter.items
        }
        notifyDataSetChanged()
    }

    private fun getThumbnail(plate: Plate): String? {
        return if (plate.images.isNotEmpty() && plate.images[0].isNotEmpty()) {
            plate.images.firstOrNull()
        } else {
            null
        }
    }
}