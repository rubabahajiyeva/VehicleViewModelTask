package com.rubabe.task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubabe.task.adapter.ManufacturerAdapter
import com.rubabe.task.databinding.ActivityManufacturerBinding
import com.rubabe.task.model.VehicleViewModel

class ManufacturerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManufacturerBinding
    lateinit var viewModel: VehicleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManufacturerBinding.inflate(layoutInflater)
        binding.manufacturerSkipButton.setOnClickListener {
            startActivity(Intent(this, ModelsForMakeYearActivity::class.java))
        }
        setContentView(binding.root)
        binding.manufacturerRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this)[VehicleViewModel::class.java]

        binding.manufacturerSearch.setOnClickListener {
            viewModel.getMakeForManufacturerDetails(this, binding.manufacturerEditText.text.toString())
        }

        viewModel.manufacturerLiveData.observe(this, Observer { manufacturerDetails ->
            binding.manufacturerRecyclerView.adapter = viewModel.manufacturerLiveData.value?.Results?.let {
                ManufacturerAdapter(
                   this, it
                )
            }
        })
    }
}