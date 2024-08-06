package com.wilmer.roomcrono.k_form.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormRadioButtonElement
import com.thejuki.kformmaster.viewholder.FormRadioButtonViewHolder
import com.wilmer.roomcrono.R

public class FormAdapter(private val elements: List<BaseFormElement<*>>, private val listener: ((BaseFormElement<*>) -> Unit)?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_radio_button_element, parent, false)
        return FormRadioButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val element = elements[position]
        if (holder is FormRadioButtonViewHolder && element is FormRadioButtonElement<*>) {
            holder.bind(element as FormRadioButtonElement<String>, listener)
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }
}
