package com.e.cyberpaydemo.di

import android.app.Application
import androidx.room.Room
import com.e.cyberpaydemo.api.LoanApi
import com.e.cyberpaydemo.data.LoanDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(LoanApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun providerLoanApi(retrofit: Retrofit): LoanApi =
        retrofit.create(LoanApi::class.java)

    @Provides
    @Singleton
    fun providerDatabase(app: Application): LoanDatabase =
        Room.databaseBuilder(app, LoanDatabase::class.java, "loan_database")
            .build()
}