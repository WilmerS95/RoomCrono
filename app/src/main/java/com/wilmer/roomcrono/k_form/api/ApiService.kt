package com.wilmer.roomcrono.k_form.api

import com.thejuki.kformmaster.form_api.model.FormData
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("radio")
    fun getFormData(): Call<List<FormData>>
}