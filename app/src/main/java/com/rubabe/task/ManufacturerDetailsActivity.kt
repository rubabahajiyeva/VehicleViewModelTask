package com.rubabe.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubabe.task.adapter.ManufacturerDetailsAdapter
import com.rubabe.task.databinding.ActivityManufacturerDetailsBinding
import com.rubabe.task.viewmodel.VehicleViewModel

class ManufacturerDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManufacturerDetailsBinding
    lateinit var viewModel: VehicleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManufacturerDetailsBinding.inflate(layoutInflater)
        binding.manufacturerButton.setOnClickListener {
            startActivity(Intent(this, ManufacturerActivity::class.java))
        }
        setContentView(binding.root)
        binding.manufacturerDetailsRecyclerRow.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this)[VehicleViewModel::class.java]

        binding.searchButton.setOnClickListener {
            viewModel.getManufacturerDetails(this, binding.carTypeEditText.text.toString())
        }

        viewModel.carManufacturerLiveData.observe(this, Observer { VehicleManufacturerDetails ->
            binding.manufacturerDetailsRecyclerRow.adapter = viewModel.carManufacturerLiveData.value?.Results?.let {
                ManufacturerDetailsAdapter(
                    it
                )
            }
        })
    }
}