package com.gaspol.expert.ui.country

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaspol.expert.data.Resource
import com.gaspol.expert.databinding.ActivityCountryBinding
import com.gaspol.expert.viewmodel.ViewModelFactory

class CountryActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel
    private lateinit var binding: ActivityCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val countryAdapter = CountryAdapter()
        countryAdapter.onItemClick = { selectedData ->
            Toast.makeText(this@CountryActivity, selectedData.name, Toast.LENGTH_SHORT).show()
        }

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[CountryViewModel::class.java]

        viewModel.countries.observe(this, { country ->
            if (country != null){
                when (country){
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        countryAdapter.setData(country.data)
                    }
                }
            }
        })

        with(binding.rvCountry){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = countryAdapter
        }
    }
}