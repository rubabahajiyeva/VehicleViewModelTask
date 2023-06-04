package com.rubabe.task

import android.annotation.SuppressLint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubabe.task.adapter.ManufacturerByYearAdapter
import com.rubabe.task.databinding.ActivityModelsForMakeYearBinding
import com.rubabe.task.model.VehicleViewModel

class ModelsForMakeYearActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModelsForMakeYearBinding
    lateinit var viewModel: VehicleViewModel
    private var manufacturerName = arrayListOf("mercedes","tesla", "bmw")
    private var modelYear = arrayListOf(2001, 2002, 2003, 2013)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelsForMakeYearBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Spinner
        val spinnerNameAdapter = ArrayAdapter<String>(this, R.layout.spinner_row,R.id.spinnerNameItem, manufacturerName)
        binding.spinnerName.adapter = spinnerNameAdapter
        val spinnerYearAdapter = ArrayAdapter<Int>(this, R.layout.spinner_row,R.id.spinnerYearItem, modelYear)
        binding.spinnerYear.adapter = spinnerYearAdapter

        binding.recyclerViewByYear.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this)[VehicleViewModel::class.java]


        viewModel.byYearLiveData.observe(this, Observer { byYearDetails->
            binding.recyclerViewByYear.adapter = viewModel.byYearLiveData.value?.Results?.let {
               ManufacturerByYearAdapter(
                   this, it
                )
            }
        })

        binding.searchBy.setOnClickListener {
            viewModel.getModelsForMakeYearDetails(this@ModelsForMakeYearActivity, binding.spinnerName.selectedItem.toString(),binding.spinnerYear.selectedItem.toString())

        }
    }

}