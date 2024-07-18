package com.wilmer.roomcrono.k_form.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.listener.OnFormElementValueChangedListener
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormHeader
import com.thejuki.kformmaster.model.FormRadioButtonElement
import com.wilmer.roomcrono.R
import com.wilmer.roomcrono.databinding.ActivityRadioButtonBinding
import com.wilmer.roomcrono.k_form.adapters.CustomFormAdapter
import com.wilmer.roomcrono.model.form.FormJson
import java.io.InputStreamReader

class RadioButtonActivity : AppCompatActivity(), OnFormElementValueChangedListener {
    private lateinit var binding: ActivityRadioButtonBinding
    private lateinit var formBuilder: FormBuildHelper
    private lateinit var adapter: CustomFormAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRadioButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        formBuilder = FormBuildHelper(listener = this, recyclerView = binding.rvRadioButton)
        setupForm()
    }

    private fun setupForm() {
        val inputStream = resources.openRawResource(R.raw.form)
        val json = InputStreamReader(inputStream).readText()
        val formElements = FormJson.parseJson(json)

        val elements = formElements.map { element ->
            when (element) {
                is com.wilmer.roomcrono.model.form.FormHeader -> {
                    FormHeader().apply {
                        title = element.title
                    }
                }
                is com.wilmer.roomcrono.model.form.FormRadioButton -> {
                    FormRadioButtonElement<String>().apply {
                        title = element.title
                        options = element.options
                        tag = element.tag
                        value = element.value
                        //layoutResource = R.layout.custom_radio_button_element
                    }
                }
                else -> null
            }
        }.filterNotNull()

        adapter = CustomFormAdapter(elements)

        binding.rvRadioButton.adapter = adapter
        binding.rvRadioButton.layoutManager = LinearLayoutManager(this)
        binding.rvRadioButton.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

        /*formBuilder.addFormElements(elements)

        binding.rvRadioButton.layoutManager = LinearLayoutManager(this)*/

        /*val layoutManager = LinearLayoutManager(this)
        binding.rvRadioButton.layoutManager = layoutManager
        binding.rvRadioButton.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))*/

    }

    override fun onValueChanged(formElement: BaseFormElement<*>) {
        val selectedValue = formElement.valueAsString
    }
}
