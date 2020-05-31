package com.example.dbstest.network

import com.example.dbstest.models.DataRepo
import com.example.dbstest.utils.Constants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface Api {
        @GET(Constants.GET_ARTICLE)
        fun getDataRepos():  Observable<List<DataRepo>>
}