//package com.example.taskids.ktor
//
//import android.net.http.HttpResponseCache.install
//import io.ktor.client.HttpClient
//import io.ktor.client.call.body
//import io.ktor.client.engine.android.Android
//import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
//import io.ktor.client.plugins.logging.LogLevel
//import io.ktor.client.plugins.logging.Logging
//import io.ktor.client.request.get
//import io.ktor.serialization.kotlinx.json.json
//import kotlinx.serialization.json.Json
//
//class UserRepository {
//    val BASE_URL = "http://192.168.2.130:8000"
//
//    private val client = HttpClient(Android) {
//        install(Logging) {
//            level = LogLevel.ALL
//        }
//        install(ContentNegotiation) {
//            json(Json {
//                ignoreUnknownKeys = true
//            })
//        }
//    }
//
//    suspend fun getAllUsers(): List<UserResponse> {
//        return client.get("$BASE_URL/api/users/users/").body()
//    }
//}
