package com.wilmer.roomcrono.model.form

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.thejuki.kformmaster.model.FormHeader
import com.thejuki.kformmaster.model.custom.FormElement
import com.thejuki.kformmaster.model.custom.FormRadioButton
import com.thejuki.kformmaster.model.custom.FormRadioButtonHeader

object FormJson {
    fun parseJson(json: String): List<FormElement> {
        val jsonArray = JsonParser.parseString(json).asJsonArray
        val formElements = mutableListOf<FormElement>()

        jsonArray.forEach { element ->
            val jsonObject = element.asJsonObject
            val type = jsonObject["type"].asString

            val formElement: FormElement = when (type) {
                "header" -> Gson().fromJson(jsonObject, FormRadioButtonHeader::class.java)
                "radioButton" -> Gson().fromJson(jsonObject, FormRadioButton::class.java)
                else -> throw IllegalArgumentException("Unknown form element type: $type")
            }

            formElements.add(formElement)
        }

        return formElements
    }
}
