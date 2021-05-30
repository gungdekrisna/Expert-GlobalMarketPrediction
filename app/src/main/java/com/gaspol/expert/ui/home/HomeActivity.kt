package com.gaspol.expert.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaspol.expert.R
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.data.source.remote.CommodityEntity
import com.gaspol.expert.databinding.ActivityHomeBinding
import com.gaspol.expert.ui.country.CountryActivity
import com.gaspol.expert.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var recentSearchAdapter : RecentSearchAdapter
    private lateinit var commodityAdapter: CommodityAdapter

    private lateinit var viewModel : HomeViewModel

    private var recentSearchEntity = RecentSearchEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = Html.fromHtml("<font color=\"#3E65F9\">" + "<b>" + getString(R.string.app_name) + "<b>" + "</font>")

        viewModel = obtainViewModel(this@HomeActivity)
        viewModel.getRecentSearch().observe(this, recentSearchObserver)

        val commodities = viewModel.getCommodities()
        val intent = Intent(this, CountryActivity::class.java)

        binding.etCommoditySearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    viewModel.queryChannel.send(s.toString())
                }
            }
        })

        viewModel.searchResult.observe(this, Observer { commodityItem ->
            val commodityName = arrayListOf<String?>()
            commodityItem?.map {
                commodityName.add(it?.name)
            }
            val adapter = ArrayAdapter(this@HomeActivity, R.layout.auto_correct_dialog, commodityName)
            adapter.notifyDataSetChanged()
            binding.etCommoditySearch.setAdapter(adapter)
        })

        binding.btnCommoditySearch.setOnClickListener {
            val query = binding.etCommoditySearch.text.toString().trim()
            recentSearchEntity.let { recentSearchQuery ->
                recentSearchQuery.text = query
            }
            viewModel.insert(recentSearchEntity as RecentSearchEntity)

            intent.putExtra(CountryActivity.EXTRA_COMMODITY, query)
            startActivity(intent)
        }

        recentSearchAdapter = RecentSearchAdapter()

        binding.rvRecentSearch.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvRecentSearch.adapter = recentSearchAdapter

        recentSearchAdapter.setOnItemClickCallback(object : RecentSearchAdapter.OnItemClickCallback {
            override fun onItemClicked(data: RecentSearchEntity) {
                intent.putExtra(CountryActivity.EXTRA_COMMODITY, data.text)
                startActivity(intent)
            }
        })

        commodityAdapter = CommodityAdapter()
        commodityAdapter.setCommodities(commodities)
        commodityAdapter.notifyDataSetChanged()
        binding.rvCommoditiesHighlight.layoutManager = LinearLayoutManager(this)
        binding.rvCommoditiesHighlight.adapter = commodityAdapter
        commodityAdapter.setOnItemClickCallback(object : CommodityAdapter.OnItemClickCallback {
            override fun onItemClicked(data: CommodityEntity) {
                intent.putExtra(CountryActivity.EXTRA_COMMODITY, data.name)
                startActivity(intent)
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
            if (recentSearchList.size > 5){
                viewModel.delete(recentSearchList[0])
            }
        }
    }
}