package com.wilmer.roomcrono.k_form.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.thejuki.kformmaster.adapter.FormAdapter
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.listener.OnFormElementValueChangedListener
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormHeader
import com.thejuki.kformmaster.model.FormRadioButtonElement
import com.thejuki.kformmaster.model.custom.FormRadioButton
import com.thejuki.kformmaster.model.custom.FormRadioButtonHeader
import com.wilmer.roomcrono.R
import com.wilmer.roomcrono.databinding.ActivityRadioButtonBinding
import java.io.InputStreamReader

class RadioButtonActivity : AppCompatActivity(), OnFormElementValueChangedListener {
    private lateinit var binding: ActivityRadioButtonBinding
    private lateinit var formBuilder: FormBuildHelper
    private lateinit var adapter: FormAdapter

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
        val formElements = com.thejuki.kformmaster.model.FormJson.parseJson(json)

        val elements = formElements.map { element ->
            when (element) {
                is FormRadioButtonHeader -> {
                    FormHeader().apply {
                        title = element.title
                    }
                }
                is FormRadioButton -> {
                    FormRadioButtonElement<String>().apply {
                        title = element.title
                        options = element.options
                        tag = element.tag
                        value = element.value
                    }
                }
                else -> null
            }
        }.filterNotNull()

        adapter = FormAdapter(elements){ element ->
            formBuilder.onValueChanged(element)
        }

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
