package com.example.appdaugia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.data.Item

class MyProductAdapter(private val items: List<Item>,
                       private val onItemClick: (Item) -> Unit) : RecyclerView.Adapter<MyProductAdapter.MyViewHolder>()
{
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txt_name: TextView = view.findViewById(R.id.txt_name)
        val txt_price: TextView = view.findViewById(R.id.txt_price)
        val txt_quantity: TextView = view.findViewById(R.id.txt_quantity)
        val txt_total_net: TextView = view.findViewById(R.id.txt_total_net)
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
        holder.txt_name.text = item.product.name
        holder.txt_price.text = item.product_price_net
        holder.txt_quantity.text = item.quantity
        holder.txt_total_net.text = item.product_price_net
        holder.txt_vat.text = item.vat
        holder.txt_gross.text = item.total_price

        // Thiết lập sự kiện click
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = items.size
}