package com.example.appdaugia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.data.ListItem

class MyOrderAdapter(private val items: List<ListItem>,
                     private val onItemClick: (ListItem) -> Unit) : RecyclerView.Adapter<MyOrderAdapter.MyViewHolder>()
{
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id_order: TextView = view.findViewById(R.id.id_order)
        val time: TextView = view.findViewById(R.id.time)
        val number: TextView = view.findViewById(R.id.number)
        val total: TextView = view.findViewById(R.id.total)
        val status: TextView = view.findViewById(R.id.status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.id_order.text = item.id_order
        holder.time.text = item.time
        holder.number.text = item.number
        holder.total.text = item.total
        holder.status.text = item.status

        // Thiết lập sự kiện click
        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount() = items.size
}