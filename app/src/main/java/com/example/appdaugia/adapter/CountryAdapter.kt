package com.example.appdaugia.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appdaugia.R
import android.widget.Filter
import com.example.appdaugia.data.CountryData

class CountryAdapter(
    private val countries: List<CountryData>,
    private val onItemClick: (CountryData) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), Filterable {

    private var filteredList: List<CountryData> = countries

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountry: TextView = itemView.findViewById(R.id.tvCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = filteredList[position]
        holder.tvCountry.text = country.name
        holder.itemView.setOnClickListener {
            onItemClick(country)
        }
    }

    override fun getItemCount(): Int = filteredList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence?): FilterResults {
                val text = query?.toString()?.trim()?.lowercase() ?: ""

                val result: List<CountryData> = if (text.isEmpty()) {
                    countries
                } else {
                    countries.filter { it.name!!.lowercase().contains(text) }
                }

                return FilterResults().apply { values = result }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(p0: CharSequence?, results: FilterResults?) {
                @Suppress("UNCHECKED_CAST")
                filteredList = results?.values as? List<CountryData> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }
}
