package com.gaspol.expert.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.gaspol.expert.R
import com.gaspol.expert.databinding.ActivityDetailBinding
import com.gaspol.expert.databinding.ActivityHomeBinding
import java.util.zip.Inflater

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(LayoutInflater)
        setContentView(binding.root)
    }
}