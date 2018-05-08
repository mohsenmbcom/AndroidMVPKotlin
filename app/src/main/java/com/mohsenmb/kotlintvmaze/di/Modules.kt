package com.mohsenmb.kotlintvmaze.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mohsenmb.kotlintvmaze.model.ShowsListInteractor
import com.mohsenmb.kotlintvmaze.model.ShowsListInteractorImpl
import com.mohsenmb.kotlintvmaze.model.TVMazeWebService
import com.mohsenmb.kotlintvmaze.presenter.ShowsListPresenter
import com.mohsenmb.kotlintvmaze.presenter.ShowsListPresenterImpl
import com.mohsenmb.kotlintvmaze.view.ShowsListView
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class ApiModule {
    private val API_BASE_URL = "http://api.tvmaze.com"

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TVMazeWebService {
        return retrofit.create(TVMazeWebService::class.java)
    }


    @Provides
    @Singleton
    fun provideRetrofit(@Named("apiBaseUrl") baseUrl: String, client: OkHttpClient, converterFactory: Converter.Factory,
                        callAdapterFactory: CallAdapter.Factory): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    @Singleton
    @Named("apiBaseUrl")
    fun provideApiBaseUrl(): String = API_BASE_URL

    @Provides
    @Singleton
    fun provideRxJavaCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()
}

@Module
class ShowsListModule(private val showsListView: ShowsListView) {

    @Provides
    fun provideShowsView(): ShowsListView = showsListView

    @Provides
    fun provideShowsListInteractor(interactor: ShowsListInteractorImpl): ShowsListInteractor = interactor

    @Provides
    fun provideShowsPresenter(showsPresenter: ShowsListPresenterImpl): ShowsListPresenter {
        showsPresenter.setView(showsListView)
        return showsPresenter
    }
}