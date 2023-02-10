package com.example.weatherapp.data.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.domain.common.Result
import com.example.weatherapp.domain.common.Result.Success
import com.example.weatherapp.domain.common.Result.Error
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkService @Inject constructor() {

    val retrofitService = Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()
        .create(Api::class.java)

    private val apiKeyInterceptor = Interceptor { chain: Interceptor.Chain ->  
        chain.proceed(
            chain.request()
            .newBuilder()
            .url(chain.request().url().newBuilder().addQueryParameter("appid", BuildConfig.API_KEY).build())
            .build()
        )
    }

    private fun getClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()

    suspend fun <T>handleRequest(requestBlock: suspend() -> Deferred<Response<T>>): Result<T> {
        val result = withContext(Dispatchers.IO) {
            try {
                val response = requestBlock().await()
                if(response.isSuccessful){
                    response.body()?.let {
                        return@withContext Success(it)
                    } ?: return@withContext Error(IOException("body == null"))
                }
                else
                    return@withContext Error(IOException("Error occurred"))
            } catch (ex: Exception) {
                ex.printStackTrace()
                return@withContext Error(ex)
            }
        }
        return result
    }
}