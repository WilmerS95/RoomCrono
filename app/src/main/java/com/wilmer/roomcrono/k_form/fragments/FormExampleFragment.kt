package com.wilmer.roomcrono.k_form.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thejuki.kformmaster.FormActivityTest
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.helper.email
import com.thejuki.kformmaster.helper.form
import com.thejuki.kformmaster.helper.password
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormEmailEditTextElement
import com.thejuki.kformmaster.model.FormPasswordEditTextElement
import com.wilmer.roomcrono.R
import com.wilmer.roomcrono.databinding.FragmentFormExampleBinding

class FormExampleFragment : Fragment() {
    private lateinit var binding: FragmentFormExampleBinding
    private lateinit var formBuilder: FormBuildHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupForm()
    }

    fun setupForm() {
// Initialize variables
        formBuilder = FormBuildHelper()
        formBuilder.attachRecyclerView(binding.rvFormExample)

        val elements: MutableList<BaseFormElement<*>> = mutableListOf()

// Declare form elements
        val emailElement = FormEmailEditTextElement(FormActivityTest.Tag.Email.ordinal).apply {
            title = getString(R.string.email)
        }

        elements.add(emailElement)

        val passwordElement = FormPasswordEditTextElement(FormActivityTest.Tag.Password.ordinal).apply {
            title = getString(R.string.password)
        }

        elements.add(passwordElement)

        // Add form elements (Form is refreshed for you)
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