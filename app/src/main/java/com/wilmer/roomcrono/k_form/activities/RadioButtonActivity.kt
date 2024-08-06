package com.wilmer.roomcrono.k_form.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.thejuki.kformmaster.adapter.FormAdapter
import com.thejuki.kformmaster.form_api.model.FormData
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.listener.OnFormElementValueChangedListener
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormRadioButtonElement
import com.wilmer.roomcrono.components.crono.AppToolbar
import com.wilmer.roomcrono.databinding.ActivityRadioButtonBinding
import com.wilmer.roomcrono.ui.theme.RoomCronoTheme
import com.wilmer.roomcrono.view_models.forms.FormViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RadioButtonActivity : AppCompatActivity(), OnFormElementValueChangedListener {

    private lateinit var binding: ActivityRadioButtonBinding
    private lateinit var adapter: FormAdapter
    private lateinit var formBuilder: FormBuildHelper

    private val formViewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeToolbar.setContent {
            RoomCronoTheme {
                AppToolbar(title = "--- Radio Button ---", showBackButton = true) {
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        binding.rvRadioButton.layoutManager = LinearLayoutManager(this)
        formBuilder = FormBuildHelper(listener = this, recyclerView = binding.rvRadioButton)
    }

    private fun observeViewModel() {
        formViewModel.formData.observe(this, Observer { formData ->
            formData?.let { updateFormElements(it) }
        })

        formViewModel.error.observe(this, Observer { error ->
            error?.let {
                Toast.makeText(this, "Error al cargar datos del formulario", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateFormElements(formData: List<FormData>) {
        val elements = formData.map { formDatum ->
            FormRadioButtonElement<String>().apply {
                title = formDatum.title
                options = formDatum.options
                tag = formDatum.tag
                value = formDatum.value
            }
        }

        adapter = FormAdapter(elements) { element ->
            formBuilder.onValueChanged(element)
        }

        binding.rvRadioButton.adapter = adapter
    }

    override fun onValueChanged(formElement: BaseFormElement<*>) {
        val selectedValue = formElement.valueAsString

        when (formElement.tag) {
            1 -> {

            }
        }
    }
}