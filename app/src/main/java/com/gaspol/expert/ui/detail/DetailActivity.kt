package com.gaspol.expert.ui.detail

import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gaspol.expert.R
import com.gaspol.expert.data.Resource
import com.gaspol.expert.databinding.ActivityDetailBinding
import com.gaspol.expert.domain.model.Country
import com.gaspol.expert.domain.model.Prediction
import com.gaspol.expert.viewmodel.ViewModelFactory
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewModel : DetailViewModel
    private var commoditySelected : String? = null
    private var countrySelected : Country? = null

    companion object {
        const val EXTRA_COMMODITY = "extra_commodity"
        const val EXTRA_COUNTRY = "extra_country"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = Html.fromHtml("<font color=\"#3E65F9\">" + "<b>" + getString(R.string.app_name) + "<b>" + "</font>")

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        commoditySelected = intent.getStringExtra(DetailActivity.EXTRA_COMMODITY)
        countrySelected = intent.getParcelableExtra<Country>(EXTRA_COUNTRY)
        binding.tvCommodityName.text = commoditySelected
        binding.tvCountryName.text = countrySelected?.name

        Glide.with(this@DetailActivity)
            .load("https://www.countryflags.io/${countrySelected?.code}/flat/64.png")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_error)
            .into(binding.ivFlag)

        val jsonObject = JSONObject()
        jsonObject.put("commodity", commoditySelected)
        jsonObject.put("country", countrySelected?.name)

        val jsonObjectString = jsonObject.toString()

        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())

        viewModel.prediction(requestBody).observe(this, { prediction ->
            if (prediction != null){
                when (prediction){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        populateChart(prediction.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError1.visibility = View.VISIBLE
                        binding.tvError1.text = prediction.message ?: getString(R.string.something_wrong)

                        binding.tvError2.visibility = View.VISIBLE
                        binding.tvError2.text = prediction.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })
    }

    private fun populateChart(prediction: Prediction?){
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .subtitle(getString(R.string.commodity_in_country, commoditySelected, countrySelected?.name))
            .backgroundColor("#1D1D1D")
            .axesTextColor("#FDFDFD")
            .dataLabelsEnabled(true)
            .categories(prediction!!.yearPredict.toTypedArray())
            .series(arrayOf(
                AASeriesElement()
                    .name(prediction.quantityName)
                    .data(prediction!!.quantityPredict.toTypedArray())
            )
            )

        val historyChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Area)
            .subtitle(getString(R.string.commodity_in_country, commoditySelected, countrySelected?.name))
            .backgroundColor("#1D1D1D")
            .axesTextColor("#FDFDFD")
            .dataLabelsEnabled(true)
            .colorsTheme(arrayOf("#06caf4", "#ffc069", "#06caf4", "#7dffc0"))
            .categories(prediction!!.year.toTypedArray())
            .series(arrayOf(
                AASeriesElement()
                    .name(prediction.quantityName)
                    .data(prediction!!.quantity.toTypedArray())
            )
            )

        binding.aaChartView.aa_drawChartWithChartModel(aaChartModel)
        binding.historyChartView.aa_drawChartWithChartModel(historyChartModel)
    }
}