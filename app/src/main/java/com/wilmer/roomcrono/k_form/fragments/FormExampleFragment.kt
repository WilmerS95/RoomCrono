package com.wilmer.roomcrono.k_form.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wilmer.roomcrono.databinding.FragmentFormExampleBinding

class FormExampleFragment : Fragment() {
    private lateinit var binding: FragmentFormExampleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormExampleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //formBuilder = FormBuilderHelper()
    }
}