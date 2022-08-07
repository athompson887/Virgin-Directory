/*
package com.athompson.virgin.module

import com.athompson.virgin.data.ApiService
import com.athompson.virgin.getURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {
    single {
        fun provideOkHttp(): OkHttpClient {
            return OkHttpClient.Builder()
                .build()
        }

        single {
            fun provideLoggingInterceptor(): HttpLoggingInterceptor {
                return HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                }
            }
        }

        single {
            fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(getURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
        }

        single {
            fun provideApiClient(retrofit: Retrofit): ApiService {
                return retrofit.create(ApiService::class.java)
            }
        }
    }
}*/
