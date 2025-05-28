package com.example.taskids.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    const val BASE_URL = "http://10.31.0.164:8000/api/" // Atualize com seu IP

    // Configuração do cliente HTTP com logging
    private val httpClient by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Log completo das requisições
        }

        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    // Instância Retrofit principal
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient) // Adiciona o cliente configurado
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Disponibiliza os serviços de forma individual
    val userService: UserService by lazy {
        retrofit.create(UserService::class.java)
    }

//    // Adicione outros serviços conforme necessário
//    val taskService: TaskService by lazy {
//        retrofit.create(TaskService::class.java)
//    }
}