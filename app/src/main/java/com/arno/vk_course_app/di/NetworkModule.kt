package com.arno.vk_course_app.di

import com.arno.vk_course_app.feature.app_list.data.api.CatalogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

        private const val BASE_URL = "http://185.103.109.134/"

        @Provides
        @Singleton
        fun provideJson(): Json = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
        }

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient {
                return OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .readTimeout(15, TimeUnit.SECONDS)
                        .addInterceptor { chain ->
                                chain.proceed(chain.request())
                        }
                        .build()
        }

        @Provides
        @Singleton
        fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
                val contentType = "application/json".toMediaType()
                return Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(json.asConverterFactory(contentType))
                        .build()
        }

        @Provides
        @Singleton
        fun provideCatalogApi(retrofit: Retrofit): CatalogApi {
                return retrofit.create(CatalogApi::class.java)
        }
}

