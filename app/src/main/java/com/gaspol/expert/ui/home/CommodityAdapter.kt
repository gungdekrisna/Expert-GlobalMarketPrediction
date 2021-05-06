package com.gaspol.expert.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gaspol.expert.R
import com.gaspol.expert.data.remote.CommodityEntity
import com.gaspol.expert.databinding.CommodityItemBinding

class CommodityAdapter : RecyclerView.Adapter<CommodityAdapter.CommodityViewHolder>() {

    private val commodityList = ArrayList<CommodityEntity>()

    fun setCommodities(commodities: List<CommodityEntity>?){
        if (commodities == null) return
        this.commodityList.clear()
        this.commodityList.addAll(commodities)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommodityViewHolder {
        val commodityItemBinding = CommodityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommodityViewHolder(commodityItemBinding)
    }

    override fun onBindViewHolder(holder: CommodityViewHolder, position: Int) {
        val commodity = commodityList[position]
        holder.bind(commodity)
    }

    override fun getItemCount(): Int  = commodityList.size

    class CommodityViewHolder(private val binding: CommodityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(commodity: CommodityEntity){
            with(binding){
                tvCommodityName.text = commodity.name

                Glide.with(itemView.context)
                    .load(commodity.img_url)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(ivCommodityImg)

                itemView.setOnClickListener{
                    /*val intent = Intent(itemView.context, DetailFilmActivity::class.java)
                    intent.putExtra(DetailFilmActivity.EXTRA_FILM, film.id)
                    itemView.context.startActivity(intent)*/
                }
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CommodityEntity)
    }
}