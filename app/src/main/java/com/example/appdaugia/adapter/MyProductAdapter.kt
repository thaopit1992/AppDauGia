package com.example.appdaugia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.data.Product

class MyProductAdapter(private val items: List<Product>,
                       private val onItemClick: (Product) -> Unit) : RecyclerView.Adapter<MyProductAdapter.MyViewHolder>()
{
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_name: TextView = view.findViewById(R.id.txt_name)
        val txt_price: TextView = view.findViewById(R.id.txt_price)
        val txt_quantity: TextView = view.findViewById(R.id.txt_quantity)
        val txt_net: TextView = view.findViewById(R.id.txt_net)
        val txt_vat: TextView = view.findViewById(R.id.txt_vat)
        val txt_gross: TextView = view.findViewById(R.id.txt_gross)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_detail, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.txt_name.text = item.name
        holder.txt_price.text = item.price
        holder.txt_quantity.text = item.quantity
        holder.txt_net.text = item.net
        holder.txt_vat.text = item.vat
        holder.txt_gross.text = item.gross

        // Thiết lập sự kiện click
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = items.size
}