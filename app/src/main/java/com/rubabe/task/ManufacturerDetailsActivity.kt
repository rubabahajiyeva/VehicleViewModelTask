package com.rubabe.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubabe.task.adapter.ManufacturerDetailsAdapter
import com.rubabe.task.databinding.ActivityManufacturerDetailsBinding
import com.rubabe.task.model.CarViewModel

class ManufacturerDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManufacturerDetailsBinding
    lateinit var viewModel: CarViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManufacturerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.manufacturerRecyclerRow.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this)[CarViewModel::class.java]

        binding.searchButton.setOnClickListener {
            viewModel.getManufacturerDetails(this, binding.carTypeEditText.text.toString(), "json")
        }

        viewModel.carManufacturerLiveData.observe(this, Observer { CarManufacturerDetails ->
            binding.manufacturerRecyclerRow.adapter = viewModel.carManufacturerLiveData.value?.Results?.let {
                ManufacturerDetailsAdapter(
                    it
                )
            }
        })
    }
}