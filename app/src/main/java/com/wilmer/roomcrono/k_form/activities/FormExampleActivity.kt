package com.wilmer.roomcrono.k_form.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thejuki.kformmaster.FormActivityTest
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormEmailEditTextElement
import com.thejuki.kformmaster.model.FormPasswordEditTextElement
import com.wilmer.roomcrono.R
import com.wilmer.roomcrono.databinding.ActivityFormExampleBinding

class FormExampleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFormExampleBinding
    private lateinit var formBuilder: FormBuildHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupForm()

    }

    fun setupForm() {

        formBuilder = FormBuildHelper()
        formBuilder.attachRecyclerView(binding.rvFormExample)

        val elements: MutableList<BaseFormElement<*>> = mutableListOf()

        val emailElement = FormEmailEditTextElement(FormActivityTest.Tag.Email.ordinal).apply {
            title = getString(R.string.email)
        }

        elements.add(emailElement)

        val passwordElement = FormPasswordEditTextElement(FormActivityTest.Tag.Password.ordinal).apply {
            title = getString(R.string.password)
        }

        elements.add(passwordElement)

        formBuilder.addFormElements(elements)

// Add form elements (Form is refreshed for you)
        /*formBuilder.addFormElements(elements)

        formBuilder = form(binding.rvFormExample) {
            email(FormActivityTest.Tag.Email.ordinal) {
                title = getString(R.string.email)
            }
            password(FormActivityTest.Tag.Password.ordinal) {
                title = getString(R.string.password)
            }
        }*/
    }
}