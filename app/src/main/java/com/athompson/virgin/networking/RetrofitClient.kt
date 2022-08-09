package com.athompson.virgin.networking
import com.athompson.virgin.application.VirginDirectoryApplication.Companion.getBaseUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get(), get()) }
    factory { provideRoomApi(get()) }
    factory { providePersonApi(get()) }
    factory { provideLoggingInterceptor() }
    single { provideRetrofit(get(), baseUrl = getBaseUrl()) }
    factory { ResponseHandler() }
}

fun provideRetrofit(okHttpClient: OkHttpClient,baseUrl:String): Retrofit {
    return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).addInterceptor(loggingInterceptor).build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BASIC
    return logger
}

fun provideRoomApi(retrofit: Retrofit): RoomApi = retrofit.create(RoomApi::class.java)
fun providePersonApi(retrofit: Retrofit): PeopleApi = retrofit.create(PeopleApi::class.java)