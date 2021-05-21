package com.gaspol.expert.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.remote.CommodityEntity
import com.gaspol.expert.databinding.ActivityHomeBinding
import com.gaspol.expert.detail.DetailActivity
import com.gaspol.expert.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var recentSearchAdapter : RecentSearchAdapter
    private lateinit var commodityAdapter: CommodityAdapter

    private var recentSearchEntity = RecentSearchEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = obtainViewModel(this@HomeActivity)
        viewModel.getRecentSearch().observe(this, recentSearchObserver)

        val commodities = viewModel.getCommodities()

        binding.btnCommoditySearch.setOnClickListener {
            val query = binding.etCommoditySearch.text.toString().trim()
            recentSearchEntity.let { recentSearchQuery ->
                recentSearchQuery.text = query
            }
            viewModel.insert(recentSearchEntity as RecentSearchEntity)

            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        recentSearchAdapter = RecentSearchAdapter()

        binding.rvRecentSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecentSearch.adapter = recentSearchAdapter

        recentSearchAdapter.setOnItemClickCallback(object : RecentSearchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: RecentSearchEntity) {
                Toast.makeText(this@HomeActivity, data.text, Toast.LENGTH_SHORT).show()
            }
        })

        commodityAdapter = CommodityAdapter()
        commodityAdapter.setCommodities(commodities)
        commodityAdapter.notifyDataSetChanged()
        binding.rvCommoditiesHighlight.layoutManager = LinearLayoutManager(this)
        binding.rvCommoditiesHighlight.adapter = commodityAdapter
        commodityAdapter.setOnItemClickCallback(object : CommodityAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CommodityEntity) {
                Toast.makeText(this@HomeActivity, data.name, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun obtainViewModel(activity: AppCompatActivity): HomeViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(HomeViewModel::class.java)
    }

    private val recentSearchObserver = Observer<List<RecentSearchEntity>> { recentSearchList ->
        if (recentSearchList != null) {
            recentSearchAdapter.setRecentSearch(recentSearchList.asReversed())
        }
    }
}