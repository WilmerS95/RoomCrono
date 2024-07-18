package com.wilmer.roomcrono.k_form.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormHeader
import com.thejuki.kformmaster.model.FormRadioButtonElement
import com.wilmer.roomcrono.R

class CustomFormAdapter(private val elements: List<BaseFormElement<*>>) :
    RecyclerView.Adapter<CustomFormAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_radio_button_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = elements[position]
        if (element is FormRadioButtonElement<*>) {
            holder.bind(element as FormRadioButtonElement<String>)
        } else if (element is FormHeader) {
            holder.bindHeader(element)
        }
    }

    override fun getItemCount(): Int {
        return elements.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textTitle: TextView = itemView.findViewById(R.id.title_option)
        private val radioGroup: RadioGroup = itemView.findViewById(R.id.radioGroup)

        fun bind(element: FormRadioButtonElement<String>) {
            textTitle.text = element.title
            val options = element.options ?: return

            radioGroup.removeAllViews()

            options.forEachIndexed { index, option ->
                val optionView = LayoutInflater.from(itemView.context)
                    .inflate(R.layout.custom_radio_button_option, radioGroup, false) as LinearLayout
                val radioButtonOption = optionView.findViewById<RadioButton>(R.id.option_radio_button)
                val optionText = optionView.findViewById<TextView>(R.id.option_text)

                optionText.text = option
                radioButtonOption.isChecked = element.value == option

                radioGroup.addView(optionView)

                if (index < options.size - 1) {
                    val divider = View(itemView.context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            1
                        )
                        setBackgroundColor(itemView.resources.getColor(android.R.color.darker_gray))
                    }
                    radioGroup.addView(divider)
                }

                radioButtonOption.setOnClickListener {
                    element.value = option
                    notifyDataSetChanged()
                }
            }
        }

        fun bindHeader(element: FormHeader) {
            textTitle.text = element.title
        }
    }
}
