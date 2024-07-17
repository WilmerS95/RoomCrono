package com.wilmer.roomcrono.k_form.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.listener.OnFormElementValueChangedListener
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormHeader
import com.thejuki.kformmaster.model.FormRadioButtonElement
import com.wilmer.roomcrono.R
import com.wilmer.roomcrono.databinding.ActivityRadioButtonBinding
import com.wilmer.roomcrono.model.form.FormJson
import java.io.InputStreamReader

class RadioButtonActivity : AppCompatActivity(), OnFormElementValueChangedListener {
    private lateinit var binding: ActivityRadioButtonBinding
    private lateinit var formBuilder: FormBuildHelper

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
                        tag = element.tag
                        title = element.title
                        options = element.options
                        value = element.value
                    }
                }
                //else -> null
            }
        }//.filterNotNull()

        formBuilder.addFormElements(elements)

        binding.rvRadioButton.layoutManager = LinearLayoutManager(this)
    }

    override fun onValueChanged(formElement: BaseFormElement<*>) {
        val selectedValue = formElement.valueAsString
    }
}
