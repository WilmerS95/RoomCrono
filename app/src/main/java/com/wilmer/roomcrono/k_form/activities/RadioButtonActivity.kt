package com.wilmer.roomcrono.k_form.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.wilmer.roomcrono.databinding.ActivityRadioButtonBinding

class RadioButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRadioButtonBinding
    private lateinit var formBuilder: FormBuildHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRadioButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupForm()
    }

    fun setupForm() {

    }
}