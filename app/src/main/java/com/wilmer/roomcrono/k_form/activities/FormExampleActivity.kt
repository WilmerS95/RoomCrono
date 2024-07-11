package com.wilmer.roomcrono.k_form.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wilmer.roomcrono.databinding.ActivityFormExampleBinding

class FormExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}