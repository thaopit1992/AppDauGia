package com.example.appdaugia.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import com.example.appdaugia.data.OrderData

class MyOrderAdapter(
    private val items: List<OrderData>,
    private val onItemClick: (OrderData) -> Unit
) : RecyclerView.Adapter<MyOrderAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idOrder: TextView = view.findViewById(R.id.id_order)
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

        holder.idOrder.text = item.code ?: "-"
        holder.time.text = item.buy_date ?: "-"
        holder.number.text = item.items_count?.toString() ?: "0"
        holder.total.text = item.total_price ?: "0"
        holder.status.text = item.payment_status ?: "-"

        // Sự kiện click item
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = items.size
}