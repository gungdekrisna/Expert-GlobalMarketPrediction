package com.gaspol.expert.ui.country

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gaspol.expert.R
import com.gaspol.expert.databinding.ItemCountryBinding
import com.gaspol.expert.domain.model.Country
import java.util.*

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.ListViewHolder>() {

    private var listData = ArrayList<Country>()
    var onItemClick: ((Country) -> Unit)? = null

    fun setData(newListData: List<Country>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryAdapter.ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun onBindViewHolder(holder: CountryAdapter.ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCountryBinding.bind(itemView)
        fun bind(data: Country) {
            with(binding) {
                tvCountryName.text = data.name
                Glide.with(itemView.context)
                    .load("https://www.countryflags.io/${data.code}/flat/64.png")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivCountryImg)
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}