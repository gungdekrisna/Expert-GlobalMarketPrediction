package com.gaspol.expert.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gaspol.expert.R
import com.gaspol.expert.databinding.ActivityDetailBinding
import com.gaspol.expert.domain.model.Country
import com.gaspol.expert.viewmodel.ViewModelFactory
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel : DetailViewModel

    companion object {
        const val EXTRA_COMMODITY = "extra_commodity"
        const val EXTRA_COUNTRY = "extra_country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val commoditySelected = intent.getStringExtra(DetailActivity.EXTRA_COMMODITY)
        val countrySelected = intent.getParcelableExtra<Country>(EXTRA_COUNTRY)
        binding.tvCommodityName.text = getString(R.string.commodity_detail, commoditySelected)

        Glide.with(this@DetailActivity)
            .load("https://www.countryflags.io/${countrySelected?.code}/flat/64.png")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(binding.ivFlag)

        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .subtitle(getString(R.string.commodity_in_country, commoditySelected, countrySelected?.name))
            .backgroundColor("#1D1D1D")
            .axesTextColor("#FDFDFD")
            .dataLabelsEnabled(true)
            .categories(arrayOf("2022", "2023", "2024"))
            .series(arrayOf(
                AASeriesElement()
                    .name("Prediction Quantity")
                    .data(arrayOf(12000, 13000, 14000))
            )
            )

        binding.aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }
}