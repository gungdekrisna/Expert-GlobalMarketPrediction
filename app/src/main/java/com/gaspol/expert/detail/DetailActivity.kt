package com.gaspol.expert.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gaspol.expert.R
import com.gaspol.expert.databinding.ActivityDetailBinding
import com.gaspol.expert.databinding.ActivityHomeBinding
import java.util.zip.Inflater

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private lateinit var viewmodel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(LayoutInflater)
        setContentView(binding.root)

        val country = resources.getString(R.string.country_name)
        val commodity = resources.getString(R.string.commodity_list_title)
        val resultPrediction = resources.getString(R.string.prediction_result)

        binding.apply {
            tvCountryName.text =country
            tvCommodityName.text = commodity
            tvResult.text = resultPrediction
        }

        viewmodel.getCountry().observe(this){
            binding.apply {
                tvCountryName.text = it.name.toString()
            }
        }
    }
}