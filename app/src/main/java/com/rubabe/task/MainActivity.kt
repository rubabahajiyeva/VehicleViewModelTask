package com.rubabe.task

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubabe.task.adapter.Adapter
import com.rubabe.task.databinding.ActivityMainBinding
import com.rubabe.task.model.CarViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: CarViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.skipButton.setOnClickListener {
            startActivity(Intent(this, ManufacturerDetailsActivity::class.java))
        }
        setContentView(binding.root)
        setContentView(binding.root)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel = ViewModelProvider(this)[CarViewModel::class.java]

        viewModel.getCar(this@MainActivity, "json")

        viewModel.carLiveData.observe(this, Observer { CarData ->
            binding.recyclerView.adapter = viewModel.carLiveData.value?.Results?.let {
                Adapter(
                    it
                )
            }
        })
    }


}