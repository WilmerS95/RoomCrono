package com.wilmer.roomcrono.repositories.form

import com.thejuki.kformmaster.form_api.model.FormData
import com.wilmer.roomcrono.k_form.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FormRepository @Inject constructor(private val apiService: ApiService) {

    fun getFormData(callback: (List<FormData>?, Throwable?) -> Unit) {
        apiService.getFormData().enqueue(object : Callback<List<FormData>> {
            override fun onResponse(call: Call<List<FormData>>, response: Response<List<FormData>>) {
                if (response.isSuccessful) {
                    callback(response.body(), null)
                } else {
                    callback(null, Exception("Error al obtener datos"))
                }
            }

            override fun onFailure(call: Call<List<FormData>>, t: Throwable) {
                callback(null, t)
            }
        })
    }
}